package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.group;

import java.io.Serializable;

/**
 * 待修改分组的商品集合对象
 */
public class ModGroupProduct implements Serializable {

    private static final long serialVersionUID = -4603228804415864052L;

    // 分组ID
    private String group_id;

    // 分组的商品集合
    private ModProduct[] product;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public ModProduct[] getProduct() {
        return product;
    }

    public void setProduct(ModProduct[] product) {
        this.product = product;
    }
}
