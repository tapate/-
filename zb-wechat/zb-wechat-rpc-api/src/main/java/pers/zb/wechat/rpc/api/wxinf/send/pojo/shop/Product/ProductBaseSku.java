package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
 * 商品SKU类
 */
public class ProductBaseSku implements Serializable {
    
    private static final long serialVersionUID = -710730442364313307L;
    
    // 商品分类SKUID
    private String id;
    
    // 商品分类SKU值ID列表
    private String[] vid;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String[] getVid() {
        return vid;
    }
    
    public void setVid(String[] vid) {
        this.vid = vid;
    }
}
