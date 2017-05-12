package pers.zb.wechat.rpc.api.wxinf.send.pojo.user;

import java.io.Serializable;
import java.util.List;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;



/**
 * 用户统计数据列表类
 */
public class UserDataList<T> extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = 1312467155642174108L;
    
    // 用户统计数据列表
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
