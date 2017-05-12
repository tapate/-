package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message;

import java.io.Serializable;

/**
 * 地理位置请求消息类
 */
public class LocationMessageRequest extends BaseMessageRequest implements
        Serializable {

    private static final long serialVersionUID = 2191341062789261658L;

    // 地理位置维度
    private String Location_X;

    // 地理位置经度
    private String Location_Y;

    // 地图缩放大小
    private String Scale;

    // 地理位置信息
    private String Label;

    public String getLocation_X() {
        return null == Location_X ? "" : Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return null == Location_Y ? "" : Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return null == Scale ? "" : Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return null == Label ? "" : Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
