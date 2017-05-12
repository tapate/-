package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

public class ProductAttrextLocation implements Serializable {

    private static final long serialVersionUID = -7622015125857130059L;

    // 国家(详见《地区列表》说明)
    private String country;

    // 省份(详见《地区列表》说明)
    private String province;

    // 城市(详见《地区列表》说明)
    private String city;

    // 地址
    private String address;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
