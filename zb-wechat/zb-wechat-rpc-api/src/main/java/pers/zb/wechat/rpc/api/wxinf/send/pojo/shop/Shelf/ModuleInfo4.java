package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件4
 */
public class ModuleInfo4 extends ModuleInfo implements Serializable {
    
    private static final long serialVersionUID = -1951710246781610582L;
    
    // 货架控件4分组列表对象
    private ModuleInfo4GroupInfos group_infos;
    
    public ModuleInfo4GroupInfos getGroup_infos() {
        return group_infos;
    }
    
    public void setGroup_infos(ModuleInfo4GroupInfos group_infos) {
        this.group_infos = group_infos;
    }
}
