package pers.zb.wechat.rpc.api.wxinf.rece.press;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.BaseMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;

/**
* 微信消息处理接口
*/
public interface IWXMessageExecutor {
    
    public BaseMessageResponse executor(BaseMessageRequest baseMessageRequest);
}
