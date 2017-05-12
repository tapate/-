package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.DeliveryTemplate;

import java.io.Serializable;

/**
 * 默认邮费计算方法对象
 */
public class DeliveryTemplateTopFeeCustom implements Serializable {
    
    private static final long serialVersionUID = 4902562877862135885L;
    
    // 起始计费数量
    private String StartStandards;
    
    // 起始计费金额(单位: 分）
    private String StartFees;
    
    // 递增计费数量
    private String AddStandards;
    
    // 递增计费金额(单位 : 分)
    private String AddFees;
    
    // 指定国家(详见《地区列表》说明)
    private String DestCountry;
    
    // 指定省份(详见《地区列表》说明)
    private String DestProvince;
    
    // 指定城市(详见《地区列表》说明)
    private String DestCity;
    
    public String getStartStandards() {
        return StartStandards;
    }
    
    public void setStartStandards(String startStandards) {
        StartStandards = startStandards;
    }
    
    public String getStartFees() {
        return StartFees;
    }
    
    public void setStartFees(String startFees) {
        StartFees = startFees;
    }
    
    public String getAddStandards() {
        return AddStandards;
    }
    
    public void setAddStandards(String addStandards) {
        AddStandards = addStandards;
    }
    
    public String getAddFees() {
        return AddFees;
    }
    
    public void setAddFees(String addFees) {
        AddFees = addFees;
    }
    
    public String getDestCountry() {
        return DestCountry;
    }
    
    public void setDestCountry(String destCountry) {
        DestCountry = destCountry;
    }
    
    public String getDestProvince() {
        return DestProvince;
    }
    
    public void setDestProvince(String destProvince) {
        DestProvince = destProvince;
    }
    
    public String getDestCity() {
        return DestCity;
    }
    
    public void setDestCity(String destCity) {
        DestCity = destCity;
    }
}
