package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Music;


/**
 * 音乐响应消息类
 */
public class MusicMessageResponse extends BaseMessageResponse implements
        Serializable {

    private static final long serialVersionUID = -1499427066455658019L;

    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
