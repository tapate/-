package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;


/**
 * 微信商品分类SKU列表类
 */
public class SkuList extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = -9102194921465423237L;
    
    // 微信商品类别SKU列表
    Sku[] skus;
    
    public Sku[] getSkus() {
        return skus;
    }

    public void setSkus(Sku[] skus) {
        this.skus = skus;
    }
}
