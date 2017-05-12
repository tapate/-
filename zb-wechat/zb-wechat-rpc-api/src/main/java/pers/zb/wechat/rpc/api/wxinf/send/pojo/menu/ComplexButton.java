package pers.zb.wechat.rpc.api.wxinf.send.pojo.menu;

import java.io.Serializable;

/**
* 复合类型的按钮类
*/
public class ComplexButton extends Button implements Serializable {
    
    private static final long serialVersionUID = 4108687112227708194L;
    
    // 子按钮集合
    private Button[] sub_button;
    
    public Button[] getSub_button() {
        return sub_button;
    }
    
    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
