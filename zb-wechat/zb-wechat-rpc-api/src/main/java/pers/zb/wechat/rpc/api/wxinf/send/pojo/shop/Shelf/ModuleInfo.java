package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件
 */
public class ModuleInfo implements Serializable {
    
    private static final long serialVersionUID = 6879760488566272807L;
    
    // 控件的ID，取值范围：1、2、3、4、5
    private String eid;
    
    public String getEid() {
        return eid;
    }
    
    public void setEid(String eid) {
        this.eid = eid;
    }
}
