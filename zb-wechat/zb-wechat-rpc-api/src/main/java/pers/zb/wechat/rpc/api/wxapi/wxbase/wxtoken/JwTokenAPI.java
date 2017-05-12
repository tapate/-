package pers.zb.wechat.rpc.api.wxapi.wxbase.wxtoken;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.core.req.WeiXinReqService;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.AccessToken;

/**
 * 微信--token信息
 */
public class JwTokenAPI {

    private static AccessToken atoken = null;

    /**
     * 获取权限令牌信息
     * 
     * @param appid
     * @param appscret
     * @return kY9Y9rfdcr8AEtYZ9gPaRUjIAuJBvXO5ZOnbv2PYFxox__uSUQcqOnaGYN1xc4N1rI7NDCaPm_0ysFYjRVnPwCJHE7v7uF_l1hI6qi6QBsA
     * @throws WexinReqException
     */
    public static String getAccessToken(String appid, String appscret) throws WexinReqException {
        String newAccessToken = "";
        atoken = new AccessToken();
        atoken.setAppid(appid);
        atoken.setSecret(appscret);
        JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(atoken);
        // 正常返回
        newAccessToken = result.getString("access_token");
        return newAccessToken;
    }

    public static void main(String[] args) {

        try {
            String s = JwTokenAPI.getAccessToken("wxf06b5852ed923210", "c98c3487158d8432916b09495888b440");
            System.out.println(s);
        } catch (WexinReqException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
