package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Image;


/**
 * 图片响应消息类
 */
public class ImageMessageResponse extends BaseMessageResponse implements
        Serializable {

    private static final long serialVersionUID = -179846782123295493L;

    // 图片
    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
