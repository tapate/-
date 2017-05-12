package pers.zb.wechat.rpc.api.wxinf.send.pojo.user;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;



/**
 * 微信用户基本信息类
 */
public class UserInfo extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = 2317755445782502928L;
    
    // 用户标识
    private String openId;
    
    // 关注状态（1：是关注，0：是未关注），未关注时无法获取其它信息
    private String subscribe;
    
    // 用户关注时间，为时间戳。如果用户曾多次关注，则取最后一次关注时间
    private String subscribeTime;
    
    // 昵称
    private String nickName;
    
    // 用户的性别（1：是男性，2：是女性，0：是未知）
    private String sex;
    
    // 用户所在国家
    private String country;
    
    // 用户所在省份
    private String province;
    
    // 用户所在城市
    private String city;
    
    // 用户的语言，简体中文为：zh_CN
    private String language;
    
    // 用户头像
    private String headImgUrl;
    
    public String getOpenId() {
        return null == openId ? "" : openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public String getSubscribe() {
        return null == subscribe ? "" : subscribe;
    }
    
    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }
    
    public String getSubscribeTime() {
        return null == subscribeTime ? "" : subscribeTime;
    }
    
    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
    
    public String getNickName() {
        return null == nickName ? "" : nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getSex() {
        return null == sex ? "" : sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getCountry() {
        return null == country ? "" : country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getProvince() {
        return null == province ? "" : province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCity() {
        return null == city ? "" : city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getLanguage() {
        return null == language ? "" : language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getHeadImgUrl() {
        return null == headImgUrl ? "" : headImgUrl;
    }
    
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
