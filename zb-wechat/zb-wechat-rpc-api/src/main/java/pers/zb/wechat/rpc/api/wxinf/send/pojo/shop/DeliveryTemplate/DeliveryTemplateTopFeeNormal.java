package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.DeliveryTemplate;

import java.io.Serializable;

/**
 * 指定地区邮费计算方法对象
 */
public class DeliveryTemplateTopFeeNormal implements Serializable {
    
    private static final long serialVersionUID = 538362785336156834L;
    
    // 起始计费数量(比如计费单位是按件, 填2代表起始计费为2件)
    private String StartStandards;
    
    // 起始计费金额(单位: 分）
    private String StartFees;
    
    // 递增计费数量
    private String AddStandards;
    
    // 递增计费金额(单位 : 分)
    private String AddFees;
    
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
}
