package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message;

import java.io.Serializable;

/**
 * 图片请求消息类
 */
public class ImageMessageRequest extends BaseMessageRequest implements
        Serializable {

    private static final long serialVersionUID = -4428126305213752458L;

    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return null == PicUrl ? "" : PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
