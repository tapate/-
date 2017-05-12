package pers.zb.wechat.rpc.api.wxinf.send.pojo.user;

import java.io.Serializable;
import java.util.List;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;



/**
 * 微信用户分组列表类
 */
public class GroupList extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = -6784561137881346021L;
    
    // 微信公共账号所有的用户分组列表
    List<Group> groups;
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
