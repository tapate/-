package pers.zb.wechat.rpc.api.wxapi.coupon.location.model;

import java.util.List;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;

@ReqType("getBatchadd")
public class Batchadd extends WeixinReqParam {
    // 门店信息
    private List<LocationList> location_list;

    public List<LocationList> getLocation_list() {
        return location_list;
    }

    public void setLocation_list(List<LocationList> location_list) {
        this.location_list = location_list;
    }

}
