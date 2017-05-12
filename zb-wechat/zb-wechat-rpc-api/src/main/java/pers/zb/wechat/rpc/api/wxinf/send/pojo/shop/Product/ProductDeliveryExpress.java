package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

public class ProductDeliveryExpress implements Serializable {

    private static final long serialVersionUID = -1157533755243643601L;

    // 快递ID
    private String id;

    // 运费(单位 : 分)
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
