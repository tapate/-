package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;

/**
 * 响应消息基类（公众帐号回复消息给普通用户）
 */
public class BaseMessageResponse implements Serializable {

    private static final long serialVersionUID = 2866417595659659193L;

    // 接收方帐号（OpenID）
    private String ToUserName;

    // 开发者微信号
    private String FromUserName;

    // 消息创建时间 （整型）
    private long CreateTime;

    // 消息类型（text/music/news）
    private String MsgType;

    public String getToUserName() {
        return null == ToUserName ? "" : ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return null == FromUserName ? "" : FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return null == MsgType ? "" : MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
