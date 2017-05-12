package pers.zb.wechat.rpc.api.wxinf.send.pojo.user;

import java.io.Serializable;

/**
 * 微信用户分组信息类
 */
public class Group implements Serializable {
    
    private static final long serialVersionUID = -197215173747377522L;
    
    // 用户分组ID
    private Integer id;
    
    // 用户分组ID
    private String name;
    
    // 分组内的用户数
    private Integer count;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return null == name ? "" : name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
}
