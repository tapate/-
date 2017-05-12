package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.DeliveryTemplate;

import java.io.Serializable;

/**
 * 具体运费计算对象
 */
public class DeliveryTemplateTopFee implements Serializable {
    
    private static final long serialVersionUID = 7247357813926935496L;
    
    // 快递类型ID(参见增加商品/快递列表)
    private String Type;
    
    // 默认邮费计算方法
    private DeliveryTemplateTopFeeNormal Normal;
    
    // 指定地区邮费计算方法
    private DeliveryTemplateTopFeeCustom[] Custom;
    
    public String getType() {
        return Type;
    }
    
    public void setType(String type) {
        Type = type;
    }
    
    public DeliveryTemplateTopFeeNormal getNormal() {
        return Normal;
    }
    
    public void setNormal(DeliveryTemplateTopFeeNormal normal) {
        Normal = normal;
    }
    
    public DeliveryTemplateTopFeeCustom[] getCustom() {
        return Custom;
    }
    
    public void setCustom(DeliveryTemplateTopFeeCustom[] custom) {
        Custom = custom;
    }
}
