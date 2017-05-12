package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
* 商品SKU值类
*/
public class SkuValue implements Serializable {
    
    private static final long serialVersionUID = -7119814350395323197L;
    
    // 商品SKU值ID
    private String id;
    
    // 商品SKU值名称
    private String name;
    
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
}
