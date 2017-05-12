package pers.zb.wechat.rpc.api.wxinf.send.pojo.user;

import java.io.Serializable;
import java.util.List;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;




/**
 * 微信关注用户列表类
 */
public class UserList extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = -3048920158171484450L;
    
    // 公众账号的总关注用户数
    private int total;
    
    // 获取的OpenId的个数
    private int count;
    
    // OpenId的列表
    private List<String> openIdList;
    
    // 获取列表的最后一个用户的OpenId
    private String nextOpenId;
    
	public int getTotal() {
		return total;
	}
    
	public void setTotal(int total) {
		this.total = total;
	}
    
	public int getCount() {
		return count;
	}
    
	public void setCount(int count) {
		this.count = count;
	}
    
	public List<String> getOpenIdList() {
		return openIdList;
	}
    
	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}
    
	public String getNextOpenId() {
        return null == nextOpenId ? "" : nextOpenId;
	}
    
	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
}
