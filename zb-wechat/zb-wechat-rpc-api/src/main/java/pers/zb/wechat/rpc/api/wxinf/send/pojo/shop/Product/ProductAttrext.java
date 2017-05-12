package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;

public class ProductAttrext implements Serializable {

    private static final long serialVersionUID = 6484580550286290206L;

    // 是否包邮(0-否, 1-是), 如果包邮delivery_info字段可省略
    private String isPostFree;

    // 是否提供发票(0-否, 1-是)
    private String isHasReceipt;

    // 是否保修(0-否, 1-是)
    private String isUnderGuaranty;

    // 是否支持退换货(0-否, 1-是)
    private String isSupportReplace;

    // 商品所在地地址
    private ProductAttrextLocation location;

    public String getIsPostFree() {
        return isPostFree;
    }

    public void setIsPostFree(String isPostFree) {
        this.isPostFree = isPostFree;
    }

    public String getIsHasReceipt() {
        return isHasReceipt;
    }

    public void setIsHasReceipt(String isHasReceipt) {
        this.isHasReceipt = isHasReceipt;
    }

    public String getIsUnderGuaranty() {
        return isUnderGuaranty;
    }

    public void setIsUnderGuaranty(String isUnderGuaranty) {
        this.isUnderGuaranty = isUnderGuaranty;
    }

    public String getIsSupportReplace() {
        return isSupportReplace;
    }

    public void setIsSupportReplace(String isSupportReplace) {
        this.isSupportReplace = isSupportReplace;
    }

    public ProductAttrextLocation getLocation() {
        return location;
    }

    public void setLocation(ProductAttrextLocation location) {
        this.location = location;
    }
}
