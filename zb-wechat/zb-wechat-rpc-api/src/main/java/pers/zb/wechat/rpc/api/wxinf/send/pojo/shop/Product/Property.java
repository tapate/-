package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
* 商品属性类
*/
public class Property implements Serializable {
    
    private static final long serialVersionUID = -1426205618389604251L;
    
    // 商品属性ID
    private String id;
    
    // 商品属性名称
    private String name;
    
    // 商品属性值列表
    PropertyValue[] propertyValues;
    
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
    
    public PropertyValue[] getPropertyValues() {
        return propertyValues;
    }
    
    public void setPropertyValues(PropertyValue[] propertyValues) {
        this.propertyValues = propertyValues;
    }
}
