package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

public class ModuleInfo1GroupInfoFilter implements Serializable {
    
    private static final long serialVersionUID = -3797884838243190790L;
    
    // 该货架控件1展示商品个数
    private String count;
    
    public String getCount() {
        return count;
    }
    
    public void setCount(String count) {
        this.count = count;
    }
}
