package pers.zb.wechat.rpc.api.wxapi.coupon.manage.model;

/**
 * 红包。
 */
public class LuckyMoney {
    // 基本的卡券数据，见下表，所有卡券通用
    private BaseInfo base_info;

    public BaseInfo getBase_info() {
        return base_info;
    }

    public void setBase_info(BaseInfo base_info) {
        this.base_info = base_info;
    }

}
