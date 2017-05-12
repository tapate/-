package pers.zb.wechat.rpc.api.wxinf.send.pojo.token;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;




/**
* 微信公众账号网页授权凭证类
*/
public class Oauth2Token extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = -7157609750077758408L;
    
    // 网页授权接口访问凭证
    private String accessToken;
    
    // 凭证有效期，单位：秒
    private int expiresIn;
    
    // 用于刷新凭证
    private String refreshToken;
    
    // 用户标识
    private String openId;
    
    // 用户授权作用域
    private String scope;
    
    public String getAccessToken() {
        return null == accessToken ? "" : accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public int getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    public String getRefreshToken() {
        return null == refreshToken ? "" : refreshToken;
    }
    
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public String getOpenId() {
        return null == openId ? "" : openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public String getScope() {
        return null == scope ? "" : scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }
}
