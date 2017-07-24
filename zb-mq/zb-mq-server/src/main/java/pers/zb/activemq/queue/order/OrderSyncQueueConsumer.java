package pers.zb.activemq.queue.order;

import java.text.MessageFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pers.zb.common.util.AjaxResult;
import pers.zb.common.util.enums.order.OrderSource;
import pers.zb.common.util.util.JsonUtil;
import pers.zb.entity.order.OrderInfo;
import pers.zb.ucenter.dao.order.OrderMapper;


/**
 * 订单同步：点对点消息消费者
 * 
 * 作者: zhoubang 
 * 日期：2015年9月28日 上午10:10:28
 */
public class OrderSyncQueueConsumer implements MessageListener {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Override
    public void onMessage(Message m) {
        //ActiveMQTextMessage om = (ActiveMQTextMessage) m;
        
        //由于消息是一个实体bean，所以使用ActiveMQObjectMessage，如果是文本，则使用ActiveMQTextMessage
        ActiveMQObjectMessage om = (ActiveMQObjectMessage) m;
        try {
            Object obj = om.getObject();
            if(obj instanceof OrderInfo){
                OrderInfo order = (OrderInfo)obj;
                logger.debug(MessageFormat.format("消费者接收到订单同步信息:{0}", JsonUtil.toJson(order)));
                
                //订单入库
                AjaxResult<String> result = new AjaxResult<String>();
                boolean bool = invalideOrder(order, result);
                if(bool){
                    order.setCreateTime(new Date());
                    order.setUpdateTime(new Date());
                    orderMapper.insert(order);
                }else{
                    logger.debug(MessageFormat.format("消费者接收到订单同步信息，订单同步异常:{0}", JsonUtil.toJson(result)));
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    
    public static boolean invalideOrder(OrderInfo order,AjaxResult<String> result){
        if(order == null){
            result.setCode(10001);
            result.setMsg("订单信息为空");
            return false;
        }
        if(StringUtils.isBlank(order.getOrderNo())){
            result.setCode(10002);
            result.setMsg("订单号不能为空");
            return false;
        }
        if(StringUtils.isBlank(order.getGoodName())){
            result.setCode(10003);
            result.setMsg("订单商品名称不能为空");
            return false;
        }
        if(order.getTotalFee() <= 0){
            result.setCode(10004);
            result.setMsg("订单金额为空或者金额小于等于0");
            return false;
        }
        if(!OrderSource.exists(order.getOrderSource())){
            result.setCode(10005);
            result.setMsg("订单来源有误");
            return false;
        }
        return true;
    }
}
