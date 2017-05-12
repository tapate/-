package pers.zb.ucenter.web.wechat.event;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.BaseEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.QRCodeEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXEventExecutor;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class ScanEventExecutor implements IWXEventExecutor {

    @Override
    public BaseMessageResponse executor(BaseEventRequest baseEventRequest) {
        System.out.println("[获取扫描二维码请求事件：]" + baseEventRequest);
        // 获取扫描二维码请求事件类
        QRCodeEventRequest qrCodeEventRequest = (QRCodeEventRequest) baseEventRequest;
        String msgType = qrCodeEventRequest.getMsgType();
        String event = qrCodeEventRequest.getEvent();
        String fromUserName = qrCodeEventRequest.getFromUserName();
        String toUserName = qrCodeEventRequest.getToUserName();
        String eventKey = qrCodeEventRequest.getEventKey();
        Long createTime = qrCodeEventRequest.getCreateTime();

        // 返回欢迎文本消息
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setFromUserName(toUserName);
        textMessageResponse.setToUserName(fromUserName);
        textMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_TEXT);
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        // textMessageResponse.setContent("欢迎你！");
        textMessageResponse.setContent("");
        return textMessageResponse;
    }

}
