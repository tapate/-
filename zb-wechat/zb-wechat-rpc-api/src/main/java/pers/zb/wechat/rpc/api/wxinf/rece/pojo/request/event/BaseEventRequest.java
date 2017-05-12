package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event;

import java.io.Serializable;

/**
 * 事件基类
 */
public class BaseEventRequest implements Serializable {

    private static final long serialVersionUID = -2108686832789387072L;

    // 开发者微信账号
    private String ToUserName;

    // 发送方帐号（一个OpenID）
    private String FromUserName;

    // 消息创建时间 （整型）
    private long CreateTime;

    // 消息类型
    private String MsgType;

    // 事件类型
    private String Event;

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

    public String getEvent() {
        return null == Event ? "" : Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
