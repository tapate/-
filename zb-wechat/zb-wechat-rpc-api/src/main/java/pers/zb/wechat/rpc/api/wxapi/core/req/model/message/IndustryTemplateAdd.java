package pers.zb.wechat.rpc.api.wxapi.core.req.model.message;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;

/**
 * 取多媒体文件
 */
@ReqType("industryTemplateAdd")
public class IndustryTemplateAdd extends WeixinReqParam {

    private String template_id_short;

    public String getTemplate_id_short() {
        return template_id_short;
    }

    public void setTemplate_id_short(String template_id_short) {
        this.template_id_short = template_id_short;
    }

}
