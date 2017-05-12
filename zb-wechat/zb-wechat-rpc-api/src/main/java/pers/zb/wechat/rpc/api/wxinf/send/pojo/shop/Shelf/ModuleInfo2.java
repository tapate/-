package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件2
 */
public class ModuleInfo2 extends ModuleInfo implements Serializable {
    
    private static final long serialVersionUID = -7378856438297173144L;
    
    // 货架控件2分组数组对象
    private ModuleInfo2GroupInfos group_infos;
    
    public ModuleInfo2GroupInfos getGroup_infos() {
        return group_infos;
    }
    
    public void setGroup_infos(ModuleInfo2GroupInfos group_infos) {
        this.group_infos = group_infos;
    }
}
