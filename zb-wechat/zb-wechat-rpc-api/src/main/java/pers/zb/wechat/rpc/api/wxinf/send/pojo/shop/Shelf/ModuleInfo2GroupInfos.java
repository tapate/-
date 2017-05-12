package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件2分组数组对象
 */
public class ModuleInfo2GroupInfos implements Serializable {

    private static final long serialVersionUID = 7596651122614193903L;

    // 货架控件2分组数组
    private ModuleInfo2GroupInfosGroup[] groups;

    public ModuleInfo2GroupInfosGroup[] getGroups() {
        return groups;
    }

    public void setGroups(ModuleInfo2GroupInfosGroup[] groups) {
        this.groups = groups;
    }
}
