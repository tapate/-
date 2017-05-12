package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架信息类
 */
public class ShelfData implements Serializable {
    
    private static final long serialVersionUID = -8844431875202465033L;
    
    // 货架控件数组对象
    private ModuleInfo[] moduleInfos;
    
    public ModuleInfo[] getModuleInfos() {
        return moduleInfos;
    }
    
    public void setModuleInfos(ModuleInfo[] moduleInfos) {
        this.moduleInfos = moduleInfos;
    }
}
