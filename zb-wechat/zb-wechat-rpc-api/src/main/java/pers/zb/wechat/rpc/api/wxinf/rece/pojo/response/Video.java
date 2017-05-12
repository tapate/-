package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response;

import java.io.Serializable;

/**
 * 视频MODEL类
 */
public class Video implements Serializable {

    private static final long serialVersionUID = -1198601122225948662L;

    // 媒体文件ID
    private String MediaId;

    // 缩略图的媒体ID
    private String ThumbMediaId;

    // 视频消息的描述
    private String Description;

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

    public String getDescription() {
        return null == Description ? "" : Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
