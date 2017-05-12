package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Image;



/**
* 发送图片请求客服消息类
*/
public class ImageMessageRequest implements Serializable {
    
    private static final long serialVersionUID = -9195773906526730889L;
    
    // 接收用户的OpenId
    private String openId;
    
    // 发送图片媒体ID
    private Image image;
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
}
