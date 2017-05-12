package pers.zb.wechat.rpc.api.wxinf.send.pojo.menu;

import java.io.Serializable;

/**
* CLICK类型的按钮类
*/
public class ClickButton extends Button implements Serializable {
    
    private static final long serialVersionUID = 5218683099270708379L;
    
    /**
     * 按钮类型：type
     * 点击推事件：                                                  WXConstants.MENU_BUTTON_TYPE_CLICK
     *     说明：用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event	的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
     * 
     * 重要：以下类型仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户，旧版本微信用户点击后将没有回应，开发者也不能正常接收到事件推送。
     * 扫码推事件：                                                  WXConstants.MENU_BUTTON_TYPE_SCANCODE_PUSH
     *     说明：用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
     * 扫码推事件且弹出“消息接收中”提示框：WXConstants.MENU_BUTTON_TYPE_SCANCODE_WAITMSG
     *     说明：用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。
     * 弹出系统拍照发图：                                      WXConstants.MENU_BUTTON_TYPE_PIC_SYSPHOTO
     *     说明：用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。
     * 弹出拍照或者相册发图：                              WXConstants.MENU_BUTTON_TYPE_PIC_PHOTO_OR_ALBUM
     *     说明：用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
     * 弹出微信相册发图器：                                  WXConstants.MENU_BUTTON_TYPE_PIC_WEIXIN
     *     说明：用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。
     * 弹出地理位置选择器：                                  WXConstants.MENU_BUTTON_TYPE_LOCATION_SELECT
     *     说明：用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
     */
    private String type;
    
    // 按钮键值
    private String key;
    
    public String getType() {
        return null == type ? "" : type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getKey() {
        return null == key ? "" : key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
}
