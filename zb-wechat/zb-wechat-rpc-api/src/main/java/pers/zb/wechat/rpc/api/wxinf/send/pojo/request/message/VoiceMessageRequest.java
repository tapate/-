package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Voice;




/**
* 发送语音请求客服消息类
*/
public class VoiceMessageRequest implements Serializable {
    
    private static final long serialVersionUID = 1464660032014262546L;
    
    // 接收用户的OpenId
    private String openId;
    
    // 发送语音媒体ID
    private Voice voice;
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public Voice getVoice() {
        return voice;
    }
    
    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
