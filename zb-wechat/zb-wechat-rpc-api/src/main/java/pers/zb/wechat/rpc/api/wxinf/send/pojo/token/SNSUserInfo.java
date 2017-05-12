package pers.zb.wechat.rpc.api.wxinf.send.pojo.token;

import java.io.Serializable;
import java.util.List;

/**
* 通过网页授权凭证获取的用户信息类
*/
public class SNSUserInfo implements Serializable {
    
    private static final long serialVersionUID = -3349594123596421959L;
    
    // 用户标识
    private String openId;
    
    // 用户昵称
    private String nickName;
    
    // 性别（1是男性，2是女性，0是未知）
    private int sex;
    
    // 国家
    private String country;
    
    // 省份
    private String province;
    
    // 城市
    private String city;
    
    // 用户头像链接
    private String headImgUrl;
    
    // 用户特权信息
    private List<String> privilegeList;
    
    public String getOpenId() {
        return null == openId ? "" : openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public String getNickName() {
        return null == nickName ? "" : nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public int getSex() {
        return sex;
    }
    
    public void setSex(int sex) {
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
    
    public String getHeadImgUrl() {
        return null == headImgUrl ? "" : headImgUrl;
    }
    
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    
    public List<String> getPrivilegeList() {
        return privilegeList;
    }
    
    public void setPrivilegeList(List<String> privilegeList) {
        this.privilegeList = privilegeList;
    }
}
