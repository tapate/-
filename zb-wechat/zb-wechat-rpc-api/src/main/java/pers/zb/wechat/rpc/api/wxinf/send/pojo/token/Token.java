package pers.zb.wechat.rpc.api.wxinf.send.pojo.token;

import java.io.Serializable;

/**
* 微信公众账号凭证类
*/
public class Token implements Serializable {
    
    private static final long serialVersionUID = 457756492445408584L;
    
    // 接口访问凭证
    private String accessToken;
    
    // 接口访问凭证获取时间
    private long tokenCreateTime;
    
    // 凭证有效期，单位：秒
    private int expiresIn;
    
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
    
    public long getTokenCreateTime() {
        return tokenCreateTime;
    }
    
    public void setTokenCreateTime(long tokenCreateTime) {
        this.tokenCreateTime = tokenCreateTime;
    }
}
