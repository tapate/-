package pers.zb.wechat.rpc.api.wxinf.send.pojo.media;

import java.io.Serializable;

/**
 * 微信媒体文件信息类
 * 
 */
public class Media implements Serializable {
    
    private static final long serialVersionUID = 5283015030573403444L;
    
    // 媒体文件类型
    private String type;
    
    // 媒体文件微信标识
    private String mediaId;
    
    // 媒体文件上传时间
    private String createdAt;
    
    public String getType() {
        return null == type ? "" : type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getMediaId() {
        return null == mediaId ? "" : mediaId;
    }
    
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    public String getCreatedAt() {
        return null == createdAt ? "" : createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
