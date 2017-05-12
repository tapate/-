package pers.zb.ucenter.web.wechat.event;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.BaseEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.MenuEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXEventExecutor;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class MenuViewEventExecutor implements IWXEventExecutor{
    
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Override
    public BaseMessageResponse executor(BaseEventRequest baseEventRequest) {
    	logger.debug("[获取用户跳转菜单请求事件：]" + baseEventRequest);
    	
        // 获取用户跳转菜单请求事件类
        MenuEventRequest menuEventRequest = (MenuEventRequest)baseEventRequest;
        //开发者微信号
        String toUserName = menuEventRequest.getToUserName();
        //发送方帐号（一个OpenID） 
        String fromUserName = menuEventRequest.getFromUserName();
        //消息创建时间 （整型）
        Long createTime = menuEventRequest.getCreateTime();
        //消息类型，event
        String msgType = menuEventRequest.getMsgType();
        //事件类型，VIEW
        String event = menuEventRequest.getEvent();
        //事件KEY值，设置的跳转URL
        String eventKey = menuEventRequest.getEventKey();
        
        logger.debug(MessageFormat.format("toUserName:{0}, fromUserName:{1}, createTime:{2}, msgType:{3}, event:{4}, eventKey:{5}"
        		,toUserName,fromUserName,createTime,msgType,event,eventKey));
        
        // 返回欢迎文本消息
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setFromUserName(toUserName);
        textMessageResponse.setToUserName(fromUserName);
        textMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_TEXT);
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        //textMessageResponse.setContent("欢迎你！");
        textMessageResponse.setContent("");
        return textMessageResponse;
    }
}
