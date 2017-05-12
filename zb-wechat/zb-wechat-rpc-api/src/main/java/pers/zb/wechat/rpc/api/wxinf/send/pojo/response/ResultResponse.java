package pers.zb.wechat.rpc.api.wxinf.send.pojo.response;

import java.io.Serializable;

/**
* 发送客服消息响应类
*/
public class ResultResponse implements Serializable {
    
    private static final long serialVersionUID = 5297911936028682372L;
    
    // 微信服务器返回的错误码
    private String errCode;
    
    // 微信服务器返回的错误信息
    private String errMsg;
    
    // 微信服务器返回的消息ID
    private Integer msgId;
    
    public String getErrCode() {
        return null == errCode ? "" : errCode;
    }
    
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    
    public String getErrMsg() {
        return null == errMsg ? "" : errMsg;
    }
    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
    public Integer getMsgId() {
        return msgId;
    }
    
    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }
}
