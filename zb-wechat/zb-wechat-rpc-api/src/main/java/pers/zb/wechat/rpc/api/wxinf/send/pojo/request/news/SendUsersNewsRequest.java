package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news;

import java.io.Serializable;

/**
* 根据用户列表群发图文消息请求类
*/
public class SendUsersNewsRequest implements Serializable {
    
    private static final long serialVersionUID = -4637215404470804947L;
    
    // 图文消息类型
    private String type;
    
    // 图文消息的媒体ID
    private String mediaId;
    
    // 图文消息创建时间
    private String createTime;
    
    // 发送图文消息的用户OpenId
    private String[] openIds;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getMediaId() {
        return mediaId;
    }
    
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String[] getOpenIds() {
        return openIds;
    }
    
    public void setOpenIds(String[] openIds) {
        this.openIds = openIds;
    }
}
