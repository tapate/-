package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event;

import java.io.Serializable;

public class NewsFinishEventRequest extends BaseEventRequest implements
        Serializable {

    private static final long serialVersionUID = -1077820890576965996L;

    // 上传群发图文消息ID
    private long MsgID;

    // 群发的结构，为“send success”或“send fail”或“err(num)”。但send
    // success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败的具体原因，可能的情况如下：
    // err(10001), 涉嫌广告 err(20001), 涉嫌政治 err(20004), 涉嫌社会 err(20002), 涉嫌色情
    // err(20006), 涉嫌违法犯罪 err(20008), 涉嫌欺诈 err(20013), 涉嫌版权 err(22000),
    // 涉嫌互推(互相宣传) err(21000), 涉嫌其他
    private String Status;

    // group_id下粉丝数；或者openid_list中的粉丝数
    private int TotalCount;

    // 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount =
    // SentCount + ErrorCount
    private int FilterCount;

    // 发送成功的粉丝数
    private int SentCount;

    // 发送失败的粉丝数
    private int ErrorCount;

    public long getMsgID() {
        return MsgID;
    }

    public void setMsgID(long msgID) {
        MsgID = msgID;
    }

    public String getStatus() {
        return null == Status ? "" : Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public int getFilterCount() {
        return FilterCount;
    }

    public void setFilterCount(int filterCount) {
        FilterCount = filterCount;
    }

    public int getSentCount() {
        return SentCount;
    }

    public void setSentCount(int sentCount) {
        SentCount = sentCount;
    }

    public int getErrorCount() {
        return ErrorCount;
    }

    public void setErrorCount(int errorCount) {
        ErrorCount = errorCount;
    }
}
