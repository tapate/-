package pers.zb.wechat.rpc.api.wxinf.send.pojo.qrcode;

import java.io.Serializable;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;



/**
 * 临时带参数二维码信息类
 */
public class QRCode extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = 2030589790076061217L;
    
    // 获取的二维码ticket
    private String ticket;
    
    // 二维码的有效时间，单位：秒，最长不超过1800秒
    private Integer expireSeconds;
    
    // 场景ID
    private Object sceneId;
    
    public String getTicket() {
        return null == ticket ? "" : ticket;
    }
    
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    
    public Integer getExpireSeconds() {
        return expireSeconds;
    }
    
    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
    
    public Object getSceneId() {
        return sceneId;
    }
    
    public void setSceneId(Object sceneId) {
        this.sceneId = sceneId;
    }
}
