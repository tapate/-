package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件5分组对象类
 */
public class ModuleInfo5GroupInfosGroup implements Serializable {
    
    private static final long serialVersionUID = -6833239893228127325L;
    
    // 货架控件5分组ID
    private String group_id;
    
    public String getGroup_id() {
        return group_id;
    }
    
    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
