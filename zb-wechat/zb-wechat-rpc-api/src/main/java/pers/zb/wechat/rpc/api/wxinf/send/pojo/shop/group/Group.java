package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.group;

import java.io.Serializable;

/**
 * 分组对象
 */
public class Group implements Serializable {
    
    private static final long serialVersionUID = -9115536766714206289L;
    
    // 分组ID
    private String group_id;
    
    // 分组名称
    private String group_name;
    
    // 商品ID集合
    private String[] product_list;
    
    public String getGroup_id() {
        return group_id;
    }
    
    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
    
    public String getGroup_name() {
        return group_name;
    }
    
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    
    public String[] getProduct_list() {
        return product_list;
    }
    
    public void setProduct_list(String[] product_list) {
        this.product_list = product_list;
    }
}
