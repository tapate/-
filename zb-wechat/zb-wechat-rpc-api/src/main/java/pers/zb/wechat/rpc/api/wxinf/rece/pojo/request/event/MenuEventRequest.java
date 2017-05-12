package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event;

import java.io.Serializable;

/**
 * 自定义菜单事件类
 */
public class MenuEventRequest extends BaseEventRequest implements Serializable {

    private static final long serialVersionUID = 234194127544078047L;

    // 菜单事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return null == EventKey ? "" : EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
