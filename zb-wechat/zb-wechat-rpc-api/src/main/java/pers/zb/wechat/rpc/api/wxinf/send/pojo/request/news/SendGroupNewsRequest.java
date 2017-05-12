package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news;

import java.io.Serializable;

/**
* 根据用户分组群发图文消息请求类
*/
public class SendGroupNewsRequest implements Serializable {
    
    private static final long serialVersionUID = 656607755012500404L;
    
    // 发送图文消息的用户分组的ID
    Integer groupId;
    
    // 发送图文消息的媒体ID
    String mediaId;
    
    public Integer getGroupId() {
        return groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    public String getMediaId() {
        return mediaId;
    }
    
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
