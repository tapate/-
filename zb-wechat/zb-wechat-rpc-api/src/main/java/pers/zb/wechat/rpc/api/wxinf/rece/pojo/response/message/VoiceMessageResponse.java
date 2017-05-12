package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Voice;



/**
 * 语音响应消息类
 */
public class VoiceMessageResponse extends BaseMessageResponse implements
        Serializable {

    private static final long serialVersionUID = -2614841843126596501L;

    // 语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
