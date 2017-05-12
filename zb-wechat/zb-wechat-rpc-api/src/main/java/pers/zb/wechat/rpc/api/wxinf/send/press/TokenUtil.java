package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.awt.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Oauth2Token;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.SNSUserInfo;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
* 凭证工具类
*/
public class TokenUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
    
    private static Token token;
    
    /**
     * 获取微信访问接口凭证（access_token默认的有效期是7200秒，也就是2个小时）
     * 
     * @return Token 微信公众账号的Token对象
     * @throws Exception
     */
    public static Token getAccessToken() throws Exception {
    	long currentTime = System.currentTimeMillis();
    	if (token == null || currentTime - token.getTokenCreateTime() >= 7200000) {
    		token = new Token();
            String appid = configManager.getConfigValue(Constants.WX_TOKEN_APPID);
            String secret = configManager.getConfigValue(Constants.WX_TOKEN_SECRET);
            String requestUrl = configManager.getConfigValue(Constants.WX_GET_ACCESS_TOKEN_URL).replace("APPID", appid).replace("SECRET", secret);
            //String result = HttpSend.post(requestUrl, null);
            //JSONObject jsonObject = JSONObject.fromObject(result);
            JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
            if (null != jsonObject) {
                String accessToken = jsonObject.getString("access_token");
                int expiresIn = jsonObject.getInt("expires_in");
                token.setAccessToken(accessToken);
                token.setExpiresIn(expiresIn);
                token.setTokenCreateTime(currentTime);
            } else {
                token = null;
            }
    	}
        return token;
    }
    
    /**
     * 刷新API接口token
     * 
     * @throws Exception
     */
    public static Token refershAPIAccessToken() throws Exception {
        long currentTime = System.currentTimeMillis();
        token = new Token();
        String appid = configManager.getConfigValue(Constants.WX_TOKEN_APPID);
        String secret = configManager.getConfigValue(Constants.WX_TOKEN_SECRET);
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_ACCESS_TOKEN_URL).replace("APPID", appid).replace("SECRET", secret);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
            String accessToken = jsonObject.getString("access_token");
            int expiresIn = jsonObject.getInt("expires_in");
            token.setAccessToken(accessToken);
            token.setExpiresIn(expiresIn);
            token.setTokenCreateTime(currentTime);
        } else {
            token = null;
        }
        return token;
    }
    
    
    
    
    /**
     * 获取网页授权凭证（access_token默认的有效期是7200秒，也就是2个小时）
     * 
     * @param code 用户同意授权CODE
     * @return Oauth2Token 网页授权凭证类
     * @throws Exception
     */
    public static Oauth2Token getOauth2AccessToken(String code) throws Exception {
        Oauth2Token oauth2Token = new Oauth2Token();
        String appid = configManager.getConfigValue(Constants.WX_TOKEN_APPID);
        String secret = configManager.getConfigValue(Constants.WX_TOKEN_SECRET);
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_OAUTH2_ACCESS_TOKEN_URL).replace("CODE", code).replace("APPID", appid).replace("SECRET", secret);
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
     * 刷新网页授权凭证（access_token默认的有效期是7200秒，也就是2个小时）
     * 
     * @param refreshToken 用户刷新access_token
     * @return Oauth2Token 网页授权凭证类
     * @throws Exception
     */
    public static Oauth2Token refershOauth2AccessToken(String refreshToken) throws Exception {
        Oauth2Token oauth2Token = new Oauth2Token();
        String appid = configManager.getConfigValue(Constants.WX_TOKEN_APPID);
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_REFERSH_OAUTH2_ACCESS_TOKEN_URL).replace("REFERSH_TOKEN", refreshToken).replace("APPID", appid);
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
    
    /**
     * 通过网页授权凭证获取用户信息
     * 
     * @param accessToken 网页授权凭证
     * @param openId 用户的OpenId
     * @return SNSUserInfo 网页授权凭证获取用户信息类
     * @throws Exception
     */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static SNSUserInfo getOauth2SNSUserInfo(String accessToken, String openId) throws Exception {
        SNSUserInfo snsUserInfo = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_OAUTH2_USER_INFO_URL).replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
            snsUserInfo = new SNSUserInfo();
            snsUserInfo.setOpenId(jsonObject.getString("openid"));
            snsUserInfo.setNickName(jsonObject.getString("nickname"));
            snsUserInfo.setSex(jsonObject.getInt("sex"));
            snsUserInfo.setCountry(jsonObject.getString("country"));
            snsUserInfo.setProvince(jsonObject.getString("province"));
            snsUserInfo.setCity(jsonObject.getString("city"));
            snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
        } else {
            snsUserInfo = null;
            // int errorCode = jsonObject.getInt("errcode");
            // String errorMsg = jsonObject.getString("errmsg");
        }
        return snsUserInfo;
    }
	
    /**
     * 获取微信服务器IP地址
     * 
     * @param token 微信公众账号的Token对象
     * @return String[] 微信服务器IP地址集合
     * @throws Exception
     */
	public static String[] getCallBackIp(Token token) throws Exception {
		String[] callBackIps = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_CALL_BACK_IP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        JSONArray jSONArray = null;
        try {
        	if (null != jsonObject && (jSONArray = jsonObject.getJSONArray("ip_list")) != null) {
        		callBackIps = new String[jSONArray.size()];
        		for (int i = 0; i < jSONArray.size(); i++) {
        			callBackIps[i] = jSONArray.getString(i);
				}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return callBackIps;
    }
}
