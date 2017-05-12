package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

public class ProductDelivery implements Serializable {

    private static final long serialVersionUID = 5931244239553510991L;

    // 运费类型(0-使用下面express字段的默认模板, 1-使用template_id代表的邮费模板, 详见邮费模板相关API)
    private String delivery_type;

    // 邮费模板ID
    private String template_id;

    private ProductDeliveryExpress[] express;

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public ProductDeliveryExpress[] getExpress() {
        return express;
    }

    public void setExpress(ProductDeliveryExpress[] express) {
        this.express = express;
    }
}
