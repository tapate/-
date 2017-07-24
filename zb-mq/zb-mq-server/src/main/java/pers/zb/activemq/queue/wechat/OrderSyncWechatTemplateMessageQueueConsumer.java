package pers.zb.activemq.queue.wechat;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import pers.zb.common.util.activemq.order.OrderSync;
import pers.zb.common.util.util.HttpSend;
import pers.zb.common.util.util.JsonUtil;
import pers.zb.common.util.util.MoneyUtil;
import pers.zb.entity.order.OrderInfo;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.TokenUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.JsonUtils;


/**
 * 订单同步：微信模板消息通知消费者
 * 
 * 作者: zhoubang 日期：2015年9月28日 上午10:10:28
 */
public class OrderSyncWechatTemplateMessageQueueConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("#{configProperties['wx.template.notice.url']}")
    public  String templateNoticeUrl; // 微信模板消息请求接口url
    
    @Value("#{configProperties['wx.order.sync.templateMessageId']}")
    private String WX_ORDER_SYNC_TEMPLATEMESSAGEID;
    
    /**
     * spring消息发送模版
     */
    @Resource(name="baseMessageConverterQueueJmsTemplate")
    private JmsTemplate jmsTemplate;
    
    @Override
    public void onMessage(Message m) {
        // 由于消息是一个实体bean，所以使用ActiveMQObjectMessage，如果是文本，则使用ActiveMQTextMessage
        ActiveMQObjectMessage om = (ActiveMQObjectMessage) m;
        try {
            Object obj = om.getObject();
            if (obj instanceof OrderSync) {
                OrderSync orderSync = (OrderSync) obj;
                logger.debug(MessageFormat.format("【订单同步：微信模板通知】消费者接收到订单信息:{0}", JsonUtil.toJson(orderSync)));
                
                OrderInfo orderInfo = JsonUtil.fromJson(orderSync.getOrderInfoJson(), OrderInfo.class);
                logger.debug(String.format("【订单同步,微信模板通知,订单信息orderInfo：】：%s", JsonUtil.toJson(orderInfo)));
                
                logger.debug(String.format("【订单同步,微信模板通知,消息模板id：】：%s", WX_ORDER_SYNC_TEMPLATEMESSAGEID));
                //组装【订单同步成功】通知的json数据
                String jsonData = "{\"touser\":\"" + orderSync.getOpenId() + "\",\"template_id\":\"" + WX_ORDER_SYNC_TEMPLATEMESSAGEID + "\",\"url\":\"http://www.2b2b92b.com\",\"topcolor\":\"#6495ED\","
                        + " \"data\":{\"first\": {\"value\":\"" + orderInfo.getOrderNo() + "\",\"color\":\"#000000\"},\"orderNo\": {\"value\":\"" + orderInfo.getOrderNo() 
                        + "\",\"color\":\"#6495ED\"},\"totalFee\": {\"value\":\"" + MoneyUtil.cent2Yuan(orderInfo.getTotalFee()) + "\",\"color\":\"#6495ED\"},\"goodName\": {\"value\":\"" + orderInfo.getGoodName()
                        + "\",\"color\":\"#6495ED\"},\"orderSource\": {\"value\":\"" + orderInfo.getOrderSource().getDescription()
                        + "\",\"color\":\"#6495ED\"},\"createTime\": {\"value\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderInfo.getCreateTime()) + "\",\"color\":\"#6495ED\"}}}";
                logger.debug("微信下单：调用发送模板消息给店员，JSON数据：" + jsonData);
                
                String sendResult = sendTemplateMsg(jsonData);
                logger.debug("微信下单：调用发送模板消息给店员，接口返回结果：" + sendResult);
                
                Map<String,Object> map = JsonUtil.fromJson(sendResult);
                
                if("ok".equals(map.get("errmsg"))){
                    logger.debug("订单同步成功，调用发送模板消息给用户，发送模板消息成功！msgid:" + map.get("msgid"));
                    
                    m.acknowledge();
                    
                    /*Connection connection = jmsTemplate.getConnectionFactory().createConnection();
                    Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
                    
                    // 获得回执地址  
                    Destination recall_destination = m.getJMSReplyTo();  
                    // 创建回执消息  
                    TextMessage textMessage = session.createTextMessage("张三，我已经收到消息了");  
                    // 以上收到消息之后，从新创建生产者，然后在回执过去  
                    MessageProducer producer = session.createProducer(recall_destination);  
                    producer.send(textMessage);  */
                    
                }else{
                    logger.debug("订单同步成功，调用发送模板消息给用户，发送模板消息失败！errcode:" + map.get("errcode"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送模板消息
     * 
     * 创建日期：2016年6月1日 下午1:50:49 操作用户：zhoubang
     * 
     * @param jsonData
     *            组装好的json数据
     * @return
     * @throws Exception
     */
    public String sendTemplateMsg(String jsonData) throws Exception {
        logger.debug(MessageFormat.format("微信模板消息通知内容：{0}", jsonData));

        // 获取token
        Token accessToken = TokenUtil.getAccessToken();
        logger.debug(MessageFormat.format("微信模板消息通知token：{0}", JsonUtils.toJson(accessToken)));

        String requestUrl = templateNoticeUrl.replace("ACCESS_TOKEN", accessToken.getAccessToken());

        String result = HttpSend.postObject(requestUrl, jsonData);
        logger.debug(MessageFormat.format("微信模板消息通知结果：{0}", result));

        // 如果出现token过期，则刷新token
        Map<String, Object> map = JsonUtils.fromJson(result);
        if (map.containsKey("errcode") && !map.get("errmsg").equals("ok")) {// 40001
            logger.debug("发送模板消息，发现token过期。" + JsonUtils.toJson(result));
            Token token = TokenUtil.refershAPIAccessToken();
            logger.debug("发送模板消息，发现token过期，刷新token，最新token如下：" + JsonUtils.toJson(token));
            // 获取token后重新发送一次模板消息
            requestUrl = templateNoticeUrl.replace("ACCESS_TOKEN", accessToken.getAccessToken());
            result = HttpSend.postObject(requestUrl, jsonData);
            logger.debug(MessageFormat.format("微信模板消息通知结果：{0}", result));
        }
        return result;
    }
}
