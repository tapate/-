package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
 * 商品属性类
 */
public class ProductBaseProperty implements Serializable {
    
    private static final long serialVersionUID = 480316604043776406L;
    
    // 商品分类属性ID
    private String id;
    
    // 商品分类属性值ID
    private String vid;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getVid() {
        return vid;
    }
    
    public void setVid(String vid) {
        this.vid = vid;
    }
}
