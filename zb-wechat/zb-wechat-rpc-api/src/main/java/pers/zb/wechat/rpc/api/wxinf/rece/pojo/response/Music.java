package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response;

import java.io.Serializable;

/**
 * 音乐MODEL类
 */
public class Music implements Serializable {

    private static final long serialVersionUID = 7546713625535821892L;

    // 音乐名称
    private String Title;

    // 音乐描述
    private String Description;

    // 音乐链接
    private String MusicUrl;

    // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String HQMusicUrl;

    // 缩略图的媒体ID，通过上传多媒体文件得到的ID
    private String ThumbMediaId;

    public String getTitle() {
        return null == Title ? "" : Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return null == Description ? "" : Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicUrl() {
        return null == MusicUrl ? "" : MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return null == HQMusicUrl ? "" : HQMusicUrl;
    }

    public void setHQMusicUrl(String hQMusicUrl) {
        HQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return null == ThumbMediaId ? "" : ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
