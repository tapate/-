package pers.zb.wechat.rpc.api.wxapi.core.handler.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.core.handler.WeiXinReqHandler;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.DownloadMedia;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqConfig;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;
import pers.zb.wechat.rpc.api.wxapi.core.util.HttpRequestProxy;
import pers.zb.wechat.rpc.api.wxapi.core.util.WeiXinReqUtil;

public class WeixinReqMediaDownHandler implements WeiXinReqHandler {

    private static Logger logger = Logger.getLogger(WeixinReqMediaDownHandler.class);

    @SuppressWarnings("rawtypes")
    public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException {
        // TODO Auto-generated method stub
        String strReturnInfo = "";
        if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
            DownloadMedia downloadMedia = (DownloadMedia) weixinReqParam;
            ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
            WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
            if (objConfig != null) {
                String reqUrl = objConfig.getUrl();
                String filePath = downloadMedia.getFilePath();
                Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
                parameters.remove("filePathName");
                strReturnInfo = HttpRequestProxy.downMadGet(reqUrl, parameters, "UTF-8", filePath,
                        downloadMedia.getMedia_id());
            }
        } else {
            logger.info("没有找到对应的配置信息");
        }
        return strReturnInfo;
    }

}
