package pers.zb.wechat.rpc.api.wxinf.send.pojo.user;

import java.io.Serializable;

/**
 * 用户增减数据类
 */
public class UserSummary implements Serializable {
    
    private static final long serialVersionUID = 6356523322834742741L;
    
    // 数据的日期
    private String ref_date;
    
    /**
     * 用户的渠道，数值代表的含义如下：
     *  0：代表其他
     * 30：代表扫二维码
     * 17：代表名片分享
     * 35：代表搜号码（即微信添加朋友页的搜索）
     * 39：代表查询微信公众帐号 
     * 43：代表图文页右上角菜单
     */
    private String user_source;
    
    // 新增的用户数量
    private String new_user;
    
    // 取消关注的用户数量，new_user减去cancel_user即为净增用户数量
    private String cancel_user;
    
    public String getRef_date() {
        return ref_date;
    }
    
    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }
    
    public String getUser_source() {
        return user_source;
    }
    
    public void setUser_source(String user_source) {
        this.user_source = user_source;
    }
    
    public String getNew_user() {
        return new_user;
    }
    
    public void setNew_user(String new_user) {
        this.new_user = new_user;
    }
    
    public String getCancel_user() {
        return cancel_user;
    }
    
    public void setCancel_user(String cancel_user) {
        this.cancel_user = cancel_user;
    }
}
