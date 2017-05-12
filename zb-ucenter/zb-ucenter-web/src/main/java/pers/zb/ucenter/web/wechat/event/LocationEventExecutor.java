package pers.zb.ucenter.web.wechat.event;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.BaseEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.LocationEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXEventExecutor;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class LocationEventExecutor implements IWXEventExecutor {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Override
    public BaseMessageResponse executor(BaseEventRequest baseEventRequest) {
        System.out.println("[获取用户定位服务请求事件：]" + baseEventRequest);
        // 获取用户定位服务请求事件类
        LocationEventRequest locationEventRequest = (LocationEventRequest)baseEventRequest;
        String msgType = locationEventRequest.getMsgType();
        String event = locationEventRequest.getEvent();
        String fromUserName = locationEventRequest.getFromUserName();
        String toUserName = locationEventRequest.getToUserName();
        Long createTime = locationEventRequest.getCreateTime();
        
        // TODO Auto-generated method stub
        
        TextMessageResponse textmessage = new TextMessageResponse();
        textmessage.setToUserName(fromUserName);
        textmessage.setFromUserName(toUserName);
        textmessage.setCreateTime(new Date().getTime());
        textmessage.setMsgType(WXConstants.RSP_MESSAGE_TYPE_TEXT);
        
        /*StringBuffer sb = new StringBuffer();
        sb.append("Hi ~感谢您关注零公里官方微信\n");
        sb.append("小蜜蜂会为您提供以下服务：\n");
        sb.append("【零公里快递】可以享受高效的快递收发服务~\n\n");
        sb.append("【零公里商城】立刻变成品味达人，享受零公里精心挑选的专供尖货~\n\n");
        sb.append("【我的账户】个人权益尽在其中！\n\n");
        sb.append("★零公里——商务楼O2O平台，竭诚为您服务！\n");
        
        textmessage.setContent(sb.toString());*/
        return textmessage;
    }
}
