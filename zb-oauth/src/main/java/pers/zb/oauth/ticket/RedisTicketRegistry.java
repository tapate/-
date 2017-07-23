package pers.zb.oauth.ticket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.AbstractDistributedTicketRegistry;

import pers.zb.oauth.util.PropertyUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTicketRegistry extends AbstractDistributedTicketRegistry {

    private static int redisDatabaseNum;

    private static String hosts;

    private static int port;

    private static int connTimeout;

    private static String redisPassword;

    /**
     * ST最大空闲时间
     */
    private static int st_time;

    /**
     * TGT最大空闲时间
     */
    private static int tgt_time;

    private static JedisPool cachePool;

    static {
        redisDatabaseNum = Integer.parseInt(PropertyUtil.getProperty("redis.database.num", "0"));
        hosts = PropertyUtil.getProperty("redis.host");
        port = Integer.parseInt(PropertyUtil.getProperty("redis.port", "3306"));
        connTimeout = Integer.parseInt(PropertyUtil.getProperty("redis.conntimeout", "2000"));
        redisPassword = PropertyUtil.getProperty("redis.pass");
        st_time = Integer.parseInt(PropertyUtil.getProperty("st.time", "2000"));
        tgt_time = Integer.parseInt(PropertyUtil.getProperty("tgt.time", "2000"));
        cachePool = new JedisPool(new JedisPoolConfig(), hosts, port, connTimeout, redisPassword);
    }

    @Override
    public void addTicket(Ticket ticket) {
        logger.debug("调用addTicket方法，ticket:" + ticket);
        logger.debug("调用addTicket方法，ticket:" + ticket.getClass().getSimpleName());
        Jedis jedis = cachePool.getResource();
        logger.debug("jedis:" + jedis);
        jedis.select(redisDatabaseNum);

        int seconds = 0;

        String key = ticket.getId();
        logger.debug("key:" + key);
        
        if (ticket instanceof TicketGrantingTicket) {
            seconds = tgt_time / 1000;
        } else {
            seconds = st_time / 1000;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(ticket);
        } catch (Exception e) {
            logger.error("adding ticket to redis error.");
        } finally {
            try {
                if (null != oos)
                    oos.close();
            } catch (Exception e) {
                logger.error("oos closing error when adding ticket to redis.");
            }
        }
        jedis.set(key.getBytes(), bos.toByteArray());
        jedis.expire(key.getBytes(), seconds);
        jedis.close();
    }

    @Override
    public boolean deleteTicket(String ticketId) {
        if (ticketId == null) {
            return false;
        }

        Jedis jedis = cachePool.getResource();
        jedis.select(redisDatabaseNum);

        jedis.del(ticketId.getBytes());
        jedis.close();
        return true;
    }

    @Override
    public Ticket getTicket(String ticketId) {
        return getProxiedTicketInstance(getRawTicket(ticketId));
    }

    private Ticket getRawTicket(final String ticketId) {
        logger.debug("调用getRawTicket方法，ticketId:" + ticketId);
        if (null == ticketId) {
            return null;
        }

        Jedis jedis = cachePool.getResource();
        jedis.select(redisDatabaseNum);
        Ticket ticket = null;
        byte[] bytes = jedis.get(ticketId.getBytes());
        if (bytes == null || bytes.length < 1) {
            return null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(bais);
            ticket = (Ticket) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getting ticket to redis error.");
        } finally {
            try {
                if (null != ois) {
                    ois.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("ois closing error when getting ticket to redis.");
            }
        }

        jedis.close();
        return ticket;
    }

    @Override
    public Collection<Ticket> getTickets() {
        throw new UnsupportedOperationException("GetTickets not supported.");
    }

    @Override
    protected boolean needsCallback() {
        return false;
    }

    @Override
    protected void updateTicket(Ticket ticket) {
        this.addTicket(ticket);
    }

}