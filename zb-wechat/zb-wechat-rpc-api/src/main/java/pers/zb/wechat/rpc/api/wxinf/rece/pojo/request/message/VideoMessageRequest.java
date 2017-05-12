package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message;

import java.io.Serializable;

/**
 * 视频请求消息类
 */
public class VideoMessageRequest extends BaseMessageRequest implements
        Serializable {

    private static final long serialVersionUID = 3100797745593131287L;

    // 视频消息媒体ID
    private String MediaId;

    // 视频消息缩略图的媒体ID
    private String ThumbMediaId;

    public String getMediaId() {
        return null == MediaId ? "" : MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return null == ThumbMediaId ? "" : ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
