package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件3
 */
public class ModuleInfo3 extends ModuleInfo implements Serializable {
    
    private static final long serialVersionUID = -5577044114472686874L;
    
    // 货架控件3分组信息对象
    private ModuleInfo3GroupInfo group_info;
    
    public ModuleInfo3GroupInfo getGroup_info() {
        return group_info;
    }
    
    public void setGroup_info(ModuleInfo3GroupInfo group_info) {
        this.group_info = group_info;
    }
}
