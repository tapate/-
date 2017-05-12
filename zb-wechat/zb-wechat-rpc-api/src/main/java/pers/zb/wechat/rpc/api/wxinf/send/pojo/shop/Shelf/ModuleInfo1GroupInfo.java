package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件1分组信息类
 */
public class ModuleInfo1GroupInfo implements Serializable {
    
    private static final long serialVersionUID = -1323114436713670822L;
    
    // 货架控件1筛选条件
    private ModuleInfo1GroupInfoFilter filter;
    
    // 货架控件1分组ID
    private String group_id;
    
    public ModuleInfo1GroupInfoFilter getFilter() {
        return filter;
    }
    
    public void setFilter(ModuleInfo1GroupInfoFilter filter) {
        this.filter = filter;
    }
    
    public String getGroup_id() {
        return group_id;
    }
    
    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
