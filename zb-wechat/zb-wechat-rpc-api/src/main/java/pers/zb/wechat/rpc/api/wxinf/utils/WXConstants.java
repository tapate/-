package pers.zb.wechat.rpc.api.wxinf.utils;

/**
* 常量工具类
*/
public class WXConstants {
    
    // 请求消息类型：文本
    public static String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：语音
    public static String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频
    public static String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：地理位置
    public static String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static String REQ_MESSAGE_TYPE_LINK = "link";
    
    // 请求消息类型：事件推送
    public static String REQ_MESSAGE_TYPE_EVENT = "event";
    
    // 事件类型：订阅
    public static String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：取消订阅
    public static String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：订阅用户扫描带参数二维码
    public static String EVENT_TYPE_SCAN = "scan";
    // 事件类型：上报地理位置
    public static String EVENT_TYPE_LOCATION = "LOCATION";
    // 事件类型：自定义菜单（点击菜单拉取消息）
    public static String EVENT_TYPE_CLICK = "CLICK";
    // 事件类型：自定义菜单（点击菜单跳转链接）
    public static String EVENT_TYPE_VIEW = "VIEW";
    // 事件类型：群发图文消息回调
    public static String EVENT_TYPE_NEWS = "MASSSENDJOBFINISH";
    
    // 响应消息类型：文本
    public static String RSP_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static String RSP_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：语音
    public static String RSP_MESSAGE_TYPE_VOICE = "voice"; 
    // 响应消息类型：视频
    public static String RSP_MESSAGE_TYPE_VIDEO = "video";
    // 响应消息类型：音乐
    public static String RSP_MESSAGE_TYPE_MUSIC = "music";
    // 响应消息类型：图文
    public static String RSP_MESSAGE_TYPE_NEWS = "news";
    // 响应消息类型：转发客服
    public static String RSP_MESSAGE_TYPE_KF = "transfer_customer_service";
    
    /**
     * 用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event	的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值， 开发者可以通过自定义的key值与用户进行交互；
     * 
     */
    // CLICK按钮类型：CLICK
    public static String MENU_BUTTON_TYPE_CLICK = "click";
    // VIEW按钮类型：VIEW
    public static String MENU_BUTTON_TYPE_VIEW = "view";
    // SCANCODE_PUSH按钮类型：SCANCODE_PUSH
    public static String MENU_BUTTON_TYPE_SCANCODE_PUSH = "scancode_push";
    // SCANCODE_WAITMSG按钮类型：SCANCODE_WAITMSG
    public static String MENU_BUTTON_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
    // PIC_SYSPHOTO按钮类型：PIC_SYSPHOTO
    public static String MENU_BUTTON_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
    // PIC_PHOTO_OR_ALBUM按钮类型：PIC_PHOTO_OR_ALBUM
    public static String MENU_BUTTON_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    // PIC_WEIXIN按钮类型：PIC_WEIXIN
    public static String MENU_BUTTON_TYPE_PIC_WEIXIN = "pic_weixin";
    // LOCATION_SELECT按钮类型：LOCATION_SELECT
    public static String MENU_BUTTON_TYPE_LOCATION_SELECT = "location_select";
    
    // 请求类型：GET
    public static String REQ_METHOD_TYPE_GET = "GET";
    // 请求类型：POST
    public static String REQ_METHOD_TYPE_POST = "POST";
}
