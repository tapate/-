package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件4分组列表类
 */
public class ModuleInfo4GroupInfos implements Serializable {
    
    private static final long serialVersionUID = -3225667730695238128L;
    
    // 货架控件4分组信息对象数组
    private ModuleInfo4GroupInfosGroups[] groups;
    
    public ModuleInfo4GroupInfosGroups[] getGroups() {
        return groups;
    }
    
    public void setGroups(ModuleInfo4GroupInfosGroups[] groups) {
        this.groups = groups;
    }
}
