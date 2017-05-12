package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
 * 商品详情图片类
 */
public class ProductBaseImgDetail extends ProductBaseDetail implements Serializable {
    
    private static final long serialVersionUID = 830101795530887615L;
    
    // 图片
    private String img;
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
}
