package pers.zb.wechat.rpc.api.wxinf.send.pojo.kf;

import java.io.Serializable;

/**
 * 客服聊天记录类
 */
public class KfRecord implements Serializable {
    
    private static final long serialVersionUID = -667704739375318445L;
    
    // 客服账号
    private String worker;
    
    // 用户的标识，对当前公众号唯一
    private String openid;
    
    // 操作ID（会话状态），具体说明见下文
    private String opercode;
    
    // 操作时间，UNIX时间戳
    private String time;
    
    // 聊天记录
    private String text;
    
    public String getWorker() {
        return worker;
    }
    
    public void setWorker(String worker) {
        this.worker = worker;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    public String getOpercode() {
        return opercode;
    }
    
    public void setOpercode(String opercode) {
        this.opercode = opercode;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
