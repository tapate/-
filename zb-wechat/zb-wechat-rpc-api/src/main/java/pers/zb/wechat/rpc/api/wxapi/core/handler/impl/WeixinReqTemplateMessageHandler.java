package pers.zb.wechat.rpc.api.wxapi.core.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.core.handler.WeiXinReqHandler;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqConfig;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.message.IndustryTemplateMessageSend;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.message.TemplateMessage;
import pers.zb.wechat.rpc.api.wxapi.core.util.HttpRequestProxy;
import pers.zb.wechat.rpc.api.wxapi.core.util.WeiXinReqUtil;

/**
 * 模板消息发送
 *
 */
public class WeixinReqTemplateMessageHandler implements WeiXinReqHandler {

    private static Logger logger = Logger.getLogger(WeixinReqTemplateMessageHandler.class);

    @SuppressWarnings("rawtypes")
    public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException {
        // TODO Auto-generated method stub
        String strReturnInfo = "";
        if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
            ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
            WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
            if (objConfig != null) {
                String reqUrl = objConfig.getUrl();
                IndustryTemplateMessageSend mc = (IndustryTemplateMessageSend) weixinReqParam;
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("access_token", mc.getAccess_token());
                String jsonData = getMsgJson(mc);
                logger.info("处理模板消息" + jsonData);
                strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
            }
        } else {
            logger.info("没有找到对应的配置信息");
        }
        return strReturnInfo;
    }

    /**
     * 单独处理 json信息
     * 
     * @param name
     * @param b
     * @return
     */
    private String getMsgJson(IndustryTemplateMessageSend mc) {
        StringBuffer json = new StringBuffer();
        Gson gson = new Gson();
        TemplateMessage tm = mc.getData();
        mc.setData(null);
        String objJson = gson.toJson(mc);
        mc.setData(tm);
        json.append(objJson);
        json.setLength(json.length() - 1);
        json.append(",");
        json.append("\"data\":{");

        objJson = gson.toJson(tm.getFirst());
        json.append(" \"first\":");
        json.append(objJson);
        json.append(",");
        objJson = gson.toJson(tm.getKeynote1());
        json.append(" \"keynote1\":");
        json.append(objJson);
        json.append(",");
        objJson = gson.toJson(tm.getKeynote2());
        json.append(" \"keynote2\":");
        json.append(objJson);
        json.append(",");
        objJson = gson.toJson(tm.getKeynote3());
        json.append(" \"keynote3\":");
        json.append(objJson);
        json.append(",");
        objJson = gson.toJson(tm.getRemark());
        json.append(" \"remark\":");
        json.append(objJson);
        json.append("}}");
        return json.toString();
    }

}
