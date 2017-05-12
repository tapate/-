package pers.zb.wechat.rpc.api.wxapi.report.datacube.model;

/**
 * 结果类--获取消息发送分布月数据
 */
public class WxDataCubeStreamMsgDistMonthInfo extends WxDataCubeStreamMsgInfo {

    private String count_interval;

    public String getCount_interval() {
        return count_interval;
    }

    public void setCount_interval(String count_interval) {
        this.count_interval = count_interval;
    }

}
