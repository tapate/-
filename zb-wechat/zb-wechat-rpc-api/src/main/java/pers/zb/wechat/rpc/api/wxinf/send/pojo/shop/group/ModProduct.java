package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.group;

import java.io.Serializable;

/**
 * 修改分组商品对象
 */
public class ModProduct implements Serializable {
    
    private static final long serialVersionUID = -5437596019323543389L;
    
    // 商品ID
    private String product_id;
    
    // 修改操作(0-删除, 1-增加)
    private String mod_action;
    
    public String getProduct_id() {
        return product_id;
    }
    
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    public String getMod_action() {
        return mod_action;
    }
    
    public void setMod_action(String mod_action) {
        this.mod_action = mod_action;
    }
}
