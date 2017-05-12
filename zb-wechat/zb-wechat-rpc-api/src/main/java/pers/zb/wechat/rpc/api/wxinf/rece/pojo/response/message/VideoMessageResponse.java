package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Video;



/**
 * 视频响应消息类
 */
public class VideoMessageResponse extends BaseMessageResponse implements
        Serializable {

    private static final long serialVersionUID = 7501831507084091058L;

    // 视频
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
