package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message;

import java.io.Serializable;

/**
* 发送视频请求客服消息类
*/
public class VideoMessageRequest implements Serializable {
    
    private static final long serialVersionUID = -6782388759574405565L;
    
    // 接收用户的OpenId
    private String openId;
    
    // 媒体文件ID
    private String mediaId;
    
    // 缩略图的媒体ID
    private String thumbMediaId;
    
    // 视频消息的标题
    private String title;
    
    // 视频消息的描述
    private String description;
    
    public String getOpenId() {
        return null == openId ? "" : openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public String getMediaId() {
        return null == mediaId ? "" : mediaId;
    }
    
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    public String getThumbMediaId() {
        return null == thumbMediaId ? "" : thumbMediaId;
    }
    
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    
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
}
