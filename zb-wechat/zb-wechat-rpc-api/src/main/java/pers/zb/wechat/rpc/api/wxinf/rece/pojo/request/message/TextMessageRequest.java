package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message;

import java.io.Serializable;

/**
 * 接收文本请求消息类
 */
public class TextMessageRequest extends BaseMessageRequest implements
        Serializable {

    private static final long serialVersionUID = 1088285230515661037L;

    // 消息内容
    private String Content;

    public String getContent() {
        return null == Content ? "" : Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
