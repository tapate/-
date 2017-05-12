package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message;

import java.io.Serializable;

/**
 * 语音请求消息类
 */
public class VoiceMessageRequest extends BaseMessageRequest implements
        Serializable {

    private static final long serialVersionUID = 5397687384017172168L;

    // 媒体ID
    private String MediaId;

    // 语音格式
    private String Format;

    // 语音识别结果
    private String Recognition;

    public String getMediaId() {
        return null == MediaId ? "" : MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return null == Format ? "" : Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getRecognition() {
        return null == Recognition ? "" : Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }
}
