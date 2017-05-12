package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
* 商品SKU类
*/
public class Sku implements Serializable {
    
    private static final long serialVersionUID = 2207207097497532969L;
    
    // 商品SKUID
    private String id;
    
    // 商品SKU名称
    private String name;
    
    // 商品SKUVALUE列表
    SkuValue[] skuValues;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public SkuValue[] getSkuValues() {
        return skuValues;
    }
    
    public void setSkuValues(SkuValue[] skuValues) {
        this.skuValues = skuValues;
    }
}
