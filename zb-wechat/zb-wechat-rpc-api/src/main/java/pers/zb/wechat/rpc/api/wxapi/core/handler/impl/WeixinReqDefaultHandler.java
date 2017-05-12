package pers.zb.wechat.rpc.api.wxapi.core.handler.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.core.handler.WeiXinReqHandler;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqConfig;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;
import pers.zb.wechat.rpc.api.wxapi.core.util.HttpRequestProxy;
import pers.zb.wechat.rpc.api.wxapi.core.util.WeiXinConstant;
import pers.zb.wechat.rpc.api.wxapi.core.util.WeiXinReqUtil;

public class WeixinReqDefaultHandler implements WeiXinReqHandler {

    private static Logger logger = Logger.getLogger(WeixinReqDefaultHandler.class);

    @SuppressWarnings("rawtypes")
    public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException {
        // TODO Auto-generated method stub
        String strReturnInfo = "";
        if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
            ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
            WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
            if (objConfig != null) {
                String reqUrl = objConfig.getUrl();
                String method = objConfig.getMethod();
                String datatype = objConfig.getDatatype();
                Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
                if (WeiXinConstant.JSON_DATA_TYPE.equalsIgnoreCase(datatype)) {
                    parameters.clear();
                    parameters.put("access_token", weixinReqParam.getAccess_token());
                    weixinReqParam.setAccess_token(null);
                    String jsonData = WeiXinReqUtil.getWeixinParamJson(weixinReqParam);
                    strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
                } else {
                    if (WeiXinConstant.REQUEST_GET.equalsIgnoreCase(method)) {
                        strReturnInfo = HttpRequestProxy.doGet(reqUrl, parameters, "UTF-8");
                    } else {
                        strReturnInfo = HttpRequestProxy.doPost(reqUrl, parameters, "UTF-8");
                    }
                }
            }
        } else {
            logger.info("没有找到对应的配置信息");
        }
        return strReturnInfo;
    }

}
