package pers.zb.ucenter.web.wechat.msg;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.BaseMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.TextMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXMessageExecutor;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class TextMessageExecutor implements IWXMessageExecutor {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Override
    public BaseMessageResponse executor(BaseMessageRequest baseMessageRequest) {
        System.out.println("[获取文本消息请求事件：]" + baseMessageRequest);
        // 获取文本消息请求事件类
        TextMessageRequest textMessageRequest = (TextMessageRequest)baseMessageRequest;
        Long msgId = textMessageRequest.getMsgId();
        String msgType = textMessageRequest.getMsgType();
        String fromUserName = textMessageRequest.getFromUserName();
        String toUserName = textMessageRequest.getToUserName();
        Long createTime = textMessageRequest.getCreateTime();
        
        TextMessageResponse textmessage = new TextMessageResponse();
        textmessage.setToUserName(fromUserName);
        textmessage.setFromUserName(toUserName);
        textmessage.setCreateTime(new Date().getTime());
        textmessage.setMsgType(WXConstants.RSP_MESSAGE_TYPE_TEXT);
        
        StringBuffer sb = new StringBuffer();
        sb.append("Hi ~感谢您关注JAVA技术分享平台\n");
        textmessage.setContent(sb.toString());
        return textmessage;
    }
}
