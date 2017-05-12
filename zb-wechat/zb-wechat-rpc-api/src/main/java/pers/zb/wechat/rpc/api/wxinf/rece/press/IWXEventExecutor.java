package pers.zb.wechat.rpc.api.wxinf.rece.press;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.BaseEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;

/**
* 微信事件处理接口
*/
public interface IWXEventExecutor {
    
    public BaseMessageResponse executor(BaseEventRequest baseEventRequest) throws Exception;
}
