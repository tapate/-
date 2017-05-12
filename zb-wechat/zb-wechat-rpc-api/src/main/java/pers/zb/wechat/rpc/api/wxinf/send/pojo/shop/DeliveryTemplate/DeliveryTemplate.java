package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.DeliveryTemplate;

import java.io.Serializable;

/**
 * 邮费模板对象
 */
public class DeliveryTemplate implements Serializable {
    
    private static final long serialVersionUID = 680495262721206416L;
    
    // 邮费模板ID
    private String template_id;
    
    // 邮费模板名称
    private String Name;
    
    // 支付方式(0-买家承担运费, 1-卖家承担运费)
    private String Assumer;
    
    // 计费单位(0-按件计费, 1-按重量计费, 2-按体积计费，目前只支持按件计费，默认为0)
    private String Valuation;
    
    // 具体运费计算
    private DeliveryTemplateTopFee[] TopFee;
    
    public String getTemplate_id() {
        return template_id;
    }
    
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String name) {
        Name = name;
    }
    
    public String getAssumer() {
        return Assumer;
    }
    
    public void setAssumer(String assumer) {
        Assumer = assumer;
    }
    
    public String getValuation() {
        return Valuation;
    }
    
    public void setValuation(String valuation) {
        Valuation = valuation;
    }
    
    public DeliveryTemplateTopFee[] getTopFee() {
        return TopFee;
    }
    
    public void setTopFee(DeliveryTemplateTopFee[] topFee) {
        TopFee = topFee;
    }
}
