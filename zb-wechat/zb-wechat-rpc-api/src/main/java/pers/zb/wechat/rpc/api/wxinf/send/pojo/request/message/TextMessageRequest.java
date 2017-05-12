package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message;

import java.io.Serializable;

/**
* 发送文本请求客服消息类
*/
public class TextMessageRequest implements Serializable {
    
    private static final long serialVersionUID = -3342414793857919481L;
    
    // 接收用户的OpenId
    private String openId;
    
    // 发送消息内容
    private String content;
    
    public String getOpenId() {
        return null == openId ? "" : openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
	public String getContent() {
        return null == content ? "" : content;
	}
    
	public void setContent(String content) {
		this.content = content;
	}
}
