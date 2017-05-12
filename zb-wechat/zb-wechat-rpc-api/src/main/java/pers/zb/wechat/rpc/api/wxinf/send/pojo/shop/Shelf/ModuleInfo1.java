package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件1
 */
public class ModuleInfo1 extends ModuleInfo implements Serializable {
    
    private static final long serialVersionUID = 6393384486520256307L;
    
    private ModuleInfo1GroupInfo group_info;
    
    public ModuleInfo1GroupInfo getGroup_info() {
        return group_info;
    }
    
    public void setGroup_info(ModuleInfo1GroupInfo group_info) {
        this.group_info = group_info;
    }
}
