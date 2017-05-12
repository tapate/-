package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
* 商品属性值类
*/
public class PropertyValue implements Serializable {
    
    private static final long serialVersionUID = 6929993678618893969L;
    
    // 商品属性值ID
    private String id;
    
    // 商品属性值名称
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
