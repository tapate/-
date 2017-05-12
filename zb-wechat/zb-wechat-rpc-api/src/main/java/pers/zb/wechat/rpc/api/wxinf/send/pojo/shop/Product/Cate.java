package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
* 商品分类
*/
public class Cate implements Serializable {
    
    private static final long serialVersionUID = -6780512619727939843L;
    
    // 商品分类ID
    private String id;
    
    // 商品分类名称
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
