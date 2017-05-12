package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;


/**
 * 微信商品分类属性列表类
 */
public class PropertyList extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = 3513702479587371789L;
    
    // 微信商品类别属性列表
    Property[] propertys;
    
    public Property[] getPropertys() {
        return propertys;
    }
    
    public void setPropertys(Property[] propertys) {
        this.propertys = propertys;
    }
}
