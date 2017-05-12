package pers.zb.wechat.rpc.api.wxapi.core.handler;

import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;

/**
 * 获取微信接口的信息
 *
 */
public interface WeiXinReqHandler {

    public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException;

}
