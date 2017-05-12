package pers.zb.wechat.rpc.api.wxinf.send.press;


import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Oauth2Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class Oauth2TokenUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
    
    /**
     * 通过code换取网页授权access_token（access_token默认的有效期是7200秒，也就是2个小时）
     * 
     * @param code
     * @return Oauth2Token 微信公众账号的网页授权access_token
     * @throws Exception
     */
    public static Oauth2Token getOauth2TokenByCode(String code) throws Exception {
    	Oauth2Token oauth2Token = null;
    	String appid = configManager.getConfigValue(Constants.WX_TOKEN_APPID);
        String secret = configManager.getConfigValue(Constants.WX_TOKEN_SECRET);
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_OAUTH2_ACCESS_TOKEN_URL).replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
            oauth2Token = new Oauth2Token();
            oauth2Token.setAccessToken(jsonObject.getString("access_token"));
            oauth2Token.setExpiresIn(jsonObject.getInt("expires_in"));
            oauth2Token.setRefreshToken(jsonObject.getString("refresh_token"));
            oauth2Token.setOpenId(jsonObject.getString("openid"));
            oauth2Token.setScope(jsonObject.getString("scope"));
        } else {
        	oauth2Token.setErrCode(jsonObject.getString("errcode"));
    		oauth2Token.setErrMsg(jsonObject.getString("errmsg"));
        }
        return oauth2Token;
    }

    /**
     * 刷新access_token（access_token默认的有效期是7200秒，也就是2个小时）
     * 
     * @param refreshToken
     * @return Oauth2Token 微信公众账号的网页授权access_token
     * @throws Exception
     */
    public static Oauth2Token refreshOauth2TokenByRefreshToken(String refreshToken) throws Exception {
    	Oauth2Token oauth2Token = null;
    	String appid = configManager.getConfigValue(Constants.WX_TOKEN_APPID);
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_REFERSH_OAUTH2_ACCESS_TOKEN_URL).replace("APPID", appid).replace("REFRESH_TOKEN", refreshToken);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
        	oauth2Token = new Oauth2Token();
        	try {
        		oauth2Token = new Oauth2Token();
                oauth2Token.setAccessToken(jsonObject.getString("access_token"));
                oauth2Token.setExpiresIn(jsonObject.getInt("expires_in"));
                oauth2Token.setRefreshToken(jsonObject.getString("refresh_token"));
                oauth2Token.setOpenId(jsonObject.getString("openid"));
                oauth2Token.setScope(jsonObject.getString("scope"));
        	} catch (Exception e) {
        		String errCode = jsonObject.getString("errcode");
        		String errMsg = jsonObject.getString("errmsg");
        		oauth2Token.setErrCode(errCode);
        		oauth2Token.setErrMsg(errMsg);
        	}
        }
        return oauth2Token;
    }
}
