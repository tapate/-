package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件5
 */
public class ModuleInfo5 extends ModuleInfo implements Serializable {
    
    private static final long serialVersionUID = -9164918586375332339L;
    
    // 货架控件5分组列表对象
    private ModuleInfo5GroupInfos group_infos;
    
    public ModuleInfo5GroupInfos getGroup_infos() {
        return group_infos;
    }
    
    public void setGroup_infos(ModuleInfo5GroupInfos group_infos) {
        this.group_infos = group_infos;
    }
}
