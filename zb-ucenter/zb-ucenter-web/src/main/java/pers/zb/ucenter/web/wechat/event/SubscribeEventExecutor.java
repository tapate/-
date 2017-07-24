package pers.zb.ucenter.web.wechat.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.common.util.util.SpringBeanUtil;
import pers.zb.entity.sys.SysUser;
import pers.zb.ucenter.rpc.api.activemq.QueueMessageService;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.BaseEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.SubscribeEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXEventExecutor;

public class SubscribeEventExecutor implements IWXEventExecutor{
    
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Override
    public BaseMessageResponse executor(BaseEventRequest baseEventRequest) throws Exception {
    	logger.debug("[获取关注事件：]" + baseEventRequest);
        
    	// 获取关注事件请求类
        SubscribeEventRequest subscribeEventRequest = (SubscribeEventRequest)baseEventRequest;
        
        //消息类型，event
        String msgType = subscribeEventRequest.getMsgType();
        logger.debug("msgType:" + msgType);
        
        //subscribe(订阅)
        String event = subscribeEventRequest.getEvent();
        logger.debug("event:" + event);
        
        //openid
        String fromUserName = subscribeEventRequest.getFromUserName();
        logger.debug("fromUserName:" + fromUserName);
        
        //开发者微信号
        String toUserName = subscribeEventRequest.getToUserName();
        logger.debug("toUserName:" + toUserName);
        
        //消息创建时间 （整型） 
        Long createTime = subscribeEventRequest.getCreateTime();
        logger.debug("createTime:" + createTime);
        
        
        logger.debug("【用户关注，发送openid给微信用户】：" + fromUserName);
        //用户关注，发送openid给微信用户
        SysUser user = new SysUser();
        user.setOpenId(fromUserName);
        
        QueueMessageService queueMessageService = (QueueMessageService)SpringBeanUtil.getApplicationContext().getBean("queueMessageService");
        queueMessageService.sendWechatSubscribeQueueMessage(user);
        
        //返回欢迎文本消息
        /*
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setFromUserName(toUserName);
        textMessageResponse.setToUserName(fromUserName);
        textMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_TEXT);
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        textMessageResponse.setContent("Hi，感谢关注车到用车，从今天开始，我们将为您提供24小时专车接送服务。");
        return textMessageResponse;
        */
        
        
        TextMessageResponse textmessage = null;
        return textmessage;
    }
}
