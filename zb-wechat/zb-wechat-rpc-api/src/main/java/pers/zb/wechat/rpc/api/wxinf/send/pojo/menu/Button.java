package pers.zb.wechat.rpc.api.wxinf.send.pojo.menu;

import java.io.Serializable;

/**
* 按钮的基类
*/
public class Button implements Serializable {
    
    private static final long serialVersionUID = 6863269144110971464L;
    
    // 按钮名称
    private String name;
    
    public String getName() {
        return null == name ? "" : name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
