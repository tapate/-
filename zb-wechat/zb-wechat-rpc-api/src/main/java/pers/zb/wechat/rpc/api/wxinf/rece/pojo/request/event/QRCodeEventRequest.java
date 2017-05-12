package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event;

import java.io.Serializable;

/**
 * 扫描带参数二维码事件类
 */
public class QRCodeEventRequest extends BaseEventRequest implements
        Serializable {

    private static final long serialVersionUID = -3226317565209620592L;

    // 事件KEY值
    private String EventKey;

    // 用于换取二维码图片
    private String Ticket;

    public String getEventKey() {
        return null == EventKey ? "" : EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return null == Ticket ? "" : Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
