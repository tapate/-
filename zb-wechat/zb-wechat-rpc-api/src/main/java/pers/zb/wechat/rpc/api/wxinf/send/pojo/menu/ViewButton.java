package pers.zb.wechat.rpc.api.wxinf.send.pojo.menu;

import java.io.Serializable;

/**
* VIEW类型的按钮类
*/
public class ViewButton extends Button implements Serializable {
    
    private static final long serialVersionUID = 1222614730552424869L;
    
    /**
     * 按钮类型：type
     * 跳转URL： WXConstants.MENU_BUTTON_TYPE_VIEW
     *    说明：用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。
     */
    private String type;
    
    // 按钮链接
    private String url;
    
    public String getType() {
        return null == type ? "" : type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUrl() {
        return null == url ? "" : url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
