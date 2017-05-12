package pers.zb.wechat.rpc.api.wxinf.send.pojo.kf;

import java.io.Serializable;

/**
 * 客服帐号类
 */
public class KfAccount implements Serializable {
    
    private static final long serialVersionUID = 5852768192693855793L;
    
    // 客服工号
    private String kf_id;
    
    // 完整客服账号，格式为：账号前缀@公众号微信号
    private String kf_account;
    
    // 客服昵称，最长6个汉字或12个英文字符
    private String nickname;
    
    // 客服账号登录密码，格式为密码明文的32位加密MD5值
    private String password;
    
    // 客服头像
    private String kf_headimg;
    
    // 客服在线状态
    private String status;
    
    // 客服设置的最大自动接入数
    private String auto_accept;
    
    // 客服当前正在接待的会话数
    private String accepted_case;
    
    public String getKf_id() {
        return kf_id;
    }
    
    public void setKf_id(String kf_id) {
        this.kf_id = kf_id;
    }
    
    public String getKf_account() {
        return kf_account;
    }
    
    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getKf_headimg() {
        return kf_headimg;
    }
    
    public void setKf_headimg(String kf_headimg) {
        this.kf_headimg = kf_headimg;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAuto_accept() {
        return auto_accept;
    }
    
    public void setAuto_accept(String auto_accept) {
        this.auto_accept = auto_accept;
    }
    
    public String getAccepted_case() {
        return accepted_case;
    }
    
    public void setAccepted_case(String accepted_case) {
        this.accepted_case = accepted_case;
    }
}
