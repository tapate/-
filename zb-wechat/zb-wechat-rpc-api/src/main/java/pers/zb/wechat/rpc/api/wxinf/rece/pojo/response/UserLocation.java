package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response;

import java.io.Serializable;

/**
 * 用户地理位置类
 */
public class UserLocation implements Serializable {

    private static final long serialVersionUID = -2846114379118841032L;

    // 微信用户标识
    private String openId;

    // 微信坐标经度
    private String lng;

    // 微信坐标纬度
    private String lat;

    // 百度坐标经度
    private String bd09Lng;

    // 百度坐标纬度
    private String bd09Lat;

    public String getOpenId() {
        return null == openId ? "" : openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLng() {
        return null == lng ? "" : lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return null == lat ? "" : lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBd09Lng() {
        return null == bd09Lng ? "" : bd09Lng;
    }

    public void setBd09Lng(String bd09Lng) {
        this.bd09Lng = bd09Lng;
    }

    public String getBd09Lat() {
        return null == bd09Lat ? "" : bd09Lat;
    }

    public void setBd09Lat(String bd09Lat) {
        this.bd09Lat = bd09Lat;
    }
}
