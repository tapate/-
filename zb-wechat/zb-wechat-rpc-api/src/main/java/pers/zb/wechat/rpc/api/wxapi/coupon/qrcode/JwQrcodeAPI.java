package pers.zb.wechat.rpc.api.wxapi.coupon.qrcode;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.core.req.WeiXinReqService;
import pers.zb.wechat.rpc.api.wxapi.coupon.qrcode.model.Getticket;
import pers.zb.wechat.rpc.api.wxapi.coupon.qrcode.model.GetticketRtn;
import pers.zb.wechat.rpc.api.wxapi.coupon.qrcode.model.QrcodeInfo;
import pers.zb.wechat.rpc.api.wxapi.coupon.qrcode.model.QrcodeRtnInfo;

/**
 * 微信卡券 - 卡券投放
 */
public class JwQrcodeAPI {

    /**
     * 创建二维码
     * 
     * @throws WexinReqException
     */
    public static QrcodeRtnInfo doAddQrcode(String accesstoken, QrcodeInfo qrcodeInfo) throws WexinReqException {
        if (accesstoken != null) {
            qrcodeInfo.setAccess_token(accesstoken);
            JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(qrcodeInfo);
            QrcodeRtnInfo qrcodeRtnInfo = (QrcodeRtnInfo) JSONObject.toBean(result, QrcodeRtnInfo.class);
            return qrcodeRtnInfo;
        }
        return null;
    }

    /**
     * 获取api_ticket
     */
    public static GetticketRtn doGetticket(String accesstoken) throws WexinReqException {
        if (accesstoken != null) {
            Getticket gk = new Getticket();
            gk.setAccess_token(accesstoken);
            JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(gk);
            GetticketRtn getticketRtn = (GetticketRtn) JSONObject.toBean(result, GetticketRtn.class);
            return getticketRtn;
        }
        return null;
    }

}
