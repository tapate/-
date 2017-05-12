package pers.zb.ucenter.web.wechat.event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.BaseEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.NewsFinishEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXEventExecutor;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class NewsFinishEventExecutor implements IWXEventExecutor{
    
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Override
    public BaseMessageResponse executor(BaseEventRequest baseEventRequest) {
        System.out.println("[获取图文消息发送完成请求事件：]" + baseEventRequest);
        // 获取图文消息发送完成请求事件类
        NewsFinishEventRequest newsFinishEventRequest = (NewsFinishEventRequest)baseEventRequest;
        String msgType = newsFinishEventRequest.getMsgType();
        String event = newsFinishEventRequest.getEvent();
        String fromUserName = newsFinishEventRequest.getFromUserName();
        String toUserName = newsFinishEventRequest.getToUserName();
        Long createTime = newsFinishEventRequest.getCreateTime();
        
        // TODO Auto-generated method stub
        
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
