package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

public class ModuleInfo2GroupInfosGroup implements Serializable {
    
    private static final long serialVersionUID = 4070711451081722891L;
    
    // 货架控件2分组ID
    private String group_id;
    
    public String getGroup_id() {
        return group_id;
    }
    
    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
