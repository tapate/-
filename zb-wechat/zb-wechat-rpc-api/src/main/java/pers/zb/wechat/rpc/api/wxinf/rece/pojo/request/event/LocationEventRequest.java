package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event;

import java.io.Serializable;

/**
 * 上报地理位置事件类
 */
public class LocationEventRequest extends BaseEventRequest implements
        Serializable {

    private static final long serialVersionUID = 1559263388415177218L;

    // 地理位置纬度
    private String Latitude;

    // 地理位置经度
    private String Longitude;

    // 地理位置精度
    private String Precision;

    public String getLatitude() {
        return null == Latitude ? "" : Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return null == Longitude ? "" : Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return null == Precision ? "" : Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
