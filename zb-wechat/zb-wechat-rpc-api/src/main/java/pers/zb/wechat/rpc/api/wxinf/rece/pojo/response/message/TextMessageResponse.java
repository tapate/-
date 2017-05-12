package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;

/**
 * 文本响应消息类
 */
public class TextMessageResponse extends BaseMessageResponse implements
        Serializable {

    private static final long serialVersionUID = -1985964191550251724L;

    // 消息内容
    private String Content;

    public String getContent() {
        return null == Content ? "" : Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
