package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response;

import java.io.Serializable;

/**
 * 图片MODEL类
 */
public class Image implements Serializable {

    private static final long serialVersionUID = -6149411225994948179L;

    // 媒体文件ID
    private String MediaId;

    public String getMediaId() {
        return null == MediaId ? "" : MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
