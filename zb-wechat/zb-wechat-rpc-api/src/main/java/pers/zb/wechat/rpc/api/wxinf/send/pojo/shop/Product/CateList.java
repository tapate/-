package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;
import java.util.List;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.group.Group;



/**
 * 微信商品类别列表类
 */
public class CateList extends ResultResponse implements Serializable {
    
    private static final long serialVersionUID = -3695351505362612355L;
    
    // 微信商品类别分组列表
    List<Group> cateList;
    
    public List<Group> getCateList() {
        return cateList;
    }
    
    public void setCateList(List<Group> cateList) {
        this.cateList = cateList;
    }
}
