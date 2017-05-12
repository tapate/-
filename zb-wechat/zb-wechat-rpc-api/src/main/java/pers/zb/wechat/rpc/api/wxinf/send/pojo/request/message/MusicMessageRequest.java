package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message;

import java.io.Serializable;

/**
* 发送音乐请求客服消息类
*/
public class MusicMessageRequest implements Serializable {
    
    private static final long serialVersionUID = -2607710994834299101L;
    
    // 接收用户的OpenId
    private String openId;
    
    // 音乐名称
    private String title;
    
    // 音乐描述
    private String description;
    
    // 音乐链接
    private String musicUrl;
    
    // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String hqMusicUrl;
    
    // 缩略图的媒体ID，通过上传多媒体文件得到的ID
    private String thumbMediaId;
    
    public String getOpenId() {
        return null == openId ? "" : openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
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
    
    public String getMusicUrl() {
        return null == musicUrl ? "" : musicUrl;
    }
    
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
    
    public String getHqMusicUrl() {
        return null == hqMusicUrl ? "" : hqMusicUrl;
    }
    
    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }
    
    public String getThumbMediaId() {
        return null == thumbMediaId ? "" : thumbMediaId;
    }
    
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
