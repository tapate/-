package pers.zb.activemq.queue.wechat;

import java.text.MessageFormat;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.common.util.util.JsonUtil;
import pers.zb.entity.sys.SysUser;
import pers.zb.wechat.rpc.api.wxapi.wxsendmsg.JwSendMessageAPI;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.TokenUtil;


/**
 * 微信关注事件，发送用户的openid微信消息：点对点消息消费者
 * 
 * 作者: zhoubang 日期：2015年9月28日 上午10:10:28
 */
public class WechatSubscribeQueueConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message m) {
        // 由于消息是一个实体bean，所以使用ActiveMQObjectMessage，如果是文本，则使用ActiveMQTextMessage
        ActiveMQObjectMessage om = (ActiveMQObjectMessage) m;
        try {
            Object obj = om.getObject();
            if (obj instanceof SysUser) {
                SysUser user = (SysUser) obj;
                logger.debug(MessageFormat.format("消费者接收到用户信息:{0}", JsonUtil.toJson(user)));
                
                //发送微信消息：告知微信用户的openid
                /*TextMessageRequest textMessageRequest = new TextMessageRequest();
                textMessageRequest.setOpenId(user.getOpenId());
                textMessageRequest.setContent(String.format("欢迎关注公众号，您的openid为 %s", user.getOpenId()));*/
                
                
                try {
                    Token token = TokenUtil.getAccessToken();
                    
                    String result = JwSendMessageAPI.messagePrivate(token.getAccessToken(), user.getOpenId(), user.getOpenId());
                    logger.debug(String.format("【微信关注，发送一条微信消息，微信返回发送结果】：%s", result));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
