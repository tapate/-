package pers.zb.ucenter.web.disconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;

@Service
@Scope("singleton")
@DisconfUpdateService(classes = { JdbcConfig.class })
public class SimpleRedisServiceUpdateCallback implements IDisconfUpdate {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SimpleRedisServiceUpdateCallback.class);

    public void reload() throws Exception {
        LOGGER.debug("数据库jdbc连接改变了.....................");
        System.out.println("数据库jdbc连接改变了.....................");
    }

}
