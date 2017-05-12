package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

/**
 * 商品详情文本类
 */
public class ProductBaseTextDetail extends ProductBaseDetail implements Serializable {
    
    private static final long serialVersionUID = 5480513006863445336L;
    
    // 文字描述
    private String text;
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
