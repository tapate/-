package pers.zb.wechat.rpc.api.wxapi.core.handler.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.Logger;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.core.handler.WeiXinReqHandler;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqConfig;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;
import pers.zb.wechat.rpc.api.wxapi.core.util.HttpRequestProxy;
import pers.zb.wechat.rpc.api.wxapi.core.util.WeiXinReqUtil;
import pers.zb.wechat.rpc.api.wxapi.coupon.location.model.LocationInfo;

public class WeixinReqLogoUploadHandler implements WeiXinReqHandler {

    private static Logger logger = Logger.getLogger(WeixinReqLogoUploadHandler.class);

    @SuppressWarnings("rawtypes")
    public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException {
        String strReturnInfo = "";
        if (weixinReqParam instanceof LocationInfo) {
            LocationInfo uploadMedia = (LocationInfo) weixinReqParam;
            ReqType reqType = uploadMedia.getClass().getAnnotation(ReqType.class);
            WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
            if (objConfig != null) {
                String reqUrl = objConfig.getUrl();
                String fileName = uploadMedia.getFilePathName();
                File file = new File(fileName);
                InputStream fileIn = null;
                try {
                    fileIn = new FileInputStream(file);
                    String extName = fileName.substring(fileName.lastIndexOf(".") + 1);// 扩展名
                    String contentType = WeiXinReqUtil.getFileContentType(extName);
                    if (contentType == null) {
                        logger.error("没有找到对应的文件类型");
                    }
                    Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
                    parameters.remove("filePathName");
                    strReturnInfo = HttpRequestProxy.uploadMedia(reqUrl, parameters, "UTF-8", fileIn, file.getName(),
                            contentType);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    throw new WexinReqException(e);
                }
            }
        } else if (weixinReqParam instanceof LocationInfo) {
            LocationInfo uploadMedia = (LocationInfo) weixinReqParam;
            ReqType reqType = uploadMedia.getClass().getAnnotation(ReqType.class);
            WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
            if (objConfig != null) {
                String reqUrl = objConfig.getUrl();
                String fileName = uploadMedia.getFilePathName();
                File file = new File(fileName);
                InputStream fileIn = null;
                try {
                    fileIn = new FileInputStream(file);
                    String extName = fileName.substring(fileName.lastIndexOf(".") + 1);// 扩展名
                    String contentType = WeiXinReqUtil.getFileContentType(extName);
                    if (contentType == null || !contentType.equals("image/jpeg")) {
                        throw new WexinReqException("上传LOGO 大小限制1MB，像素为300*300，支持JPG格式以达到最佳效果");
                    }
                    Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
                    parameters.remove("filePathName");
                    strReturnInfo = HttpRequestProxy.uploadMedia(reqUrl, parameters, "UTF-8", fileIn, file.getName(),
                            contentType);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    throw new WexinReqException(e);
                }
            }
        } else {
            logger.info("没有找到对应的配置信息");
        }
        return strReturnInfo;
    }

}
