package pers.zb.ucenter.web.wechat.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.BaseEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.UnSubscribeEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXEventExecutor;

public class UnSubscribeEventExecutor implements IWXEventExecutor {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public BaseMessageResponse executor(BaseEventRequest baseEventRequest) {
        System.out.println("[获取取消关注事件：]" + baseEventRequest);
        // 获取取消关注事件请求类
        UnSubscribeEventRequest unSubscribeEventRequest = (UnSubscribeEventRequest) baseEventRequest;
        String msgType = unSubscribeEventRequest.getMsgType();
        String event = unSubscribeEventRequest.getEvent();
        String fromUserName = unSubscribeEventRequest.getFromUserName();
        String toUserName = unSubscribeEventRequest.getToUserName();
        Long createTime = unSubscribeEventRequest.getCreateTime();

        return null;
    }
}
