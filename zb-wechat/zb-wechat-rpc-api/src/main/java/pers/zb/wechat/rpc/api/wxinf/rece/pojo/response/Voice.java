package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response;

import java.io.Serializable;

/**
 * 语音MODEL类
 */
public class Voice implements Serializable {

    private static final long serialVersionUID = 83218854191096366L;

    // 媒体文件ID
    private String MediaId;

    public String getMediaId() {
        return null == MediaId ? "" : MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
