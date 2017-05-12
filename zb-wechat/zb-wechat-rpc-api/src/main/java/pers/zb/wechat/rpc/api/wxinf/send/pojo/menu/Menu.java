package pers.zb.wechat.rpc.api.wxinf.send.pojo.menu;

import java.io.Serializable;

/**
* 微信菜单类
*/
public class Menu implements Serializable {
    
    private static final long serialVersionUID = -5103188927585355331L;
    
    // 按钮集合
    private Button[] button;
    
    public Button[] getButton() {
        return button;
    }
    
    public void setButton(Button[] button) {
        this.button = button;
    }
}
