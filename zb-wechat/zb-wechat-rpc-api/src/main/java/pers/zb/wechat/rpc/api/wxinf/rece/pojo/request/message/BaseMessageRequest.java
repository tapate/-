package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message;

import java.io.Serializable;

/**
 * 请求消息基类（普通用户发送信息给公众帐号）
 */
public class BaseMessageRequest implements Serializable {

    private static final long serialVersionUID = -6849241529106344015L;

    // 开发者微信号
    private String ToUserName;

    // 发送方帐号（OpenID）
    private String FromUserName;

    // 消息创建时间 （整型）
    private long CreateTime;

    // 消息类型（text/image/location/link/voice）
    private String MsgType;

    // 消息id，64位整型
    private long MsgId;

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

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
