package pers.zb.wechat.rpc.api.wxinf.pojo;

import java.io.Serializable;

/**
 * 百度地址信息类
 */
public class BaiduPlace implements Comparable<BaiduPlace>, Serializable {

    private static final long serialVersionUID = -2886855999655812636L;

    // 地理名称
    private String name;

    // 详细地址
    private String address;

    // 经度
    private String lng;

    // 纬度
    private String lat;

    // 联系电话
    private String telephone;

    // 距离
    private int distance;

    public String getName() {
        return null == name ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return null == address ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getTelephone() {
        return null == telephone ? "" : telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(BaiduPlace baiduPlace) {
        return this.distance - baiduPlace.getDistance();
    }
}
