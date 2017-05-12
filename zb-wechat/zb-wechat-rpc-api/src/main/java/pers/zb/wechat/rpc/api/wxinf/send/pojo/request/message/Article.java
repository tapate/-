package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message;

import java.io.Serializable;

/**
 * 图文客服消息类
 */
public class Article implements Serializable {
    
    private static final long serialVersionUID = 4697187124070635912L;
    
    // 图文消息名称
    private String title;
    
    // 图文消息描述
    private String description;
    
    // 点击图文消息跳转链接
    private String url;
    
    // 图片链接，支持JPG、PNG格式，较好的效果为大图600*300，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
    private String picUrl;
    
    public String getTitle() {
    	return null == title ? "" : title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
    	return null == description ? "" : description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getUrl() {
    	return null == url ? "" : url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getPicUrl() {
    	return null == picUrl ? "" : picUrl;
    }
    
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
