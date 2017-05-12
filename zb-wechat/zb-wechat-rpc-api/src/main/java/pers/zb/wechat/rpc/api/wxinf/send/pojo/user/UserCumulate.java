package pers.zb.wechat.rpc.api.wxinf.send.pojo.user;

import java.io.Serializable;

/**
 * 累计用户数据类
 */
public class UserCumulate implements Serializable {
    
    private static final long serialVersionUID = -4409840607401496980L;
    
    // 数据的日期
    private String ref_date;
    
    // 总用户量
    private String cumulate_user;
    
    public String getRef_date() {
        return ref_date;
    }
    
    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }
    
    public String getCumulate_user() {
        return cumulate_user;
    }
    
    public void setCumulate_user(String cumulate_user) {
        this.cumulate_user = cumulate_user;
    }
}
