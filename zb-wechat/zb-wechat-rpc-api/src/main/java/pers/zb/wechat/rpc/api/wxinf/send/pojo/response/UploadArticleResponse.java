package pers.zb.wechat.rpc.api.wxinf.send.pojo.response;

import java.io.Serializable;

/**
* 上传图文消息响应消息类
*/
public class UploadArticleResponse extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = -9188736562969256455L;
    
    // 图文消息类型
    private String type;
    
    // 图文消息的媒体ID
    private String mediaId;
    
    // 图文消息创建时间
    private String createTime;
    
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
    
    public String getCreateTime() {
        return null == createTime ? "" : createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
