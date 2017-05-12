package pers.zb.wechat.rpc.api.wxapi.core.req.model.user;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;

/**
 * 取多媒体文件
 */
@ReqType("groupUpdate")
public class GroupUpdate extends WeixinReqParam {

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
