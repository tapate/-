package pers.zb.ucenter.web.wechat.msg;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.BaseMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.ImageMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXMessageExecutor;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


public class ImageMessageExecutor implements IWXMessageExecutor {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Override
    public BaseMessageResponse executor(BaseMessageRequest baseMessageRequest) {
        System.out.println("[获取图片消息请求事件：]" + baseMessageRequest);
        // 获取图片消息请求事件类
        ImageMessageRequest imageMessageRequest = (ImageMessageRequest)baseMessageRequest;
        Long msgId = imageMessageRequest.getMsgId();
        String msgType = imageMessageRequest.getMsgType();
        String fromUserName = imageMessageRequest.getFromUserName();
        String toUserName = imageMessageRequest.getToUserName();
        Long createTime = imageMessageRequest.getCreateTime();
        
        // TODO Auto-generated method stub
        
        TextMessageResponse textmessage = new TextMessageResponse();
        textmessage.setToUserName(fromUserName);
        textmessage.setFromUserName(toUserName);
        textmessage.setCreateTime(new Date().getTime());
        textmessage.setMsgType(WXConstants.RSP_MESSAGE_TYPE_TEXT);
        
        StringBuffer sb = new StringBuffer();
        sb.append("Hi ~感谢您关注零公里官方微信\n");
        sb.append("小蜜蜂会为您提供以下服务：\n");
        sb.append("【零公里商城】享受零公里精心挑选的专供尖货~\n\n");
        sb.append("【零公里快递】可以享受高效的快递收发服务~\n\n");
        sb.append("【花样心情】鲜花预定，换种花样，换种心情！\n\n");
        sb.append("★零公里——商务楼O2O平台，竭诚为您服务！\n");
        
        textmessage.setContent(sb.toString());
        return textmessage;
    }
}
