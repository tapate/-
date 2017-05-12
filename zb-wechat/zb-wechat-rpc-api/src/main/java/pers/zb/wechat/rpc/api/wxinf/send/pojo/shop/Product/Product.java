package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
 * 商品信息类
 */
public class Product implements Serializable {
    
    private static final long serialVersionUID = 7787535998295283911L;
    
    // 商品ID
    private String product_id;
    
    // 基本属性
    private ProductBase product_base;
    
    // sku信息列表(可为多个)，每个sku信息串即为一个确定的商品，比如白色的37码的鞋子
    private ProductSku[] sku_list;
    
    // 商品其他属性
    private ProductAttrext attrext;
    
    // 运费信息
    private ProductDelivery delivery_info;
    
    public String getProduct_id() {
        return product_id;
    }
    
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    public ProductBase getProduct_base() {
        return product_base;
    }
    
    public void setProduct_base(ProductBase product_base) {
        this.product_base = product_base;
    }
    
    public ProductSku[] getSku_list() {
        return sku_list;
    }
    
    public void setSku_list(ProductSku[] sku_list) {
        this.sku_list = sku_list;
    }
    
    public ProductAttrext getAttrext() {
        return attrext;
    }
    
    public void setAttrext(ProductAttrext attrext) {
        this.attrext = attrext;
    }
    
    public ProductDelivery getDelivery_info() {
        return delivery_info;
    }
    
    public void setDelivery_info(ProductDelivery delivery_info) {
        this.delivery_info = delivery_info;
    }
}
