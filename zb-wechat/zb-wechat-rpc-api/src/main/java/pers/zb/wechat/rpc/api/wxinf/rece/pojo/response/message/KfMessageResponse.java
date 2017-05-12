package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;
import java.util.List;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Kf;


/**
 * 转发客服响应消息类
 */
public class KfMessageResponse extends BaseMessageResponse implements
        Serializable {

    private static final long serialVersionUID = 7078610745159624114L;

    // 多条客服帐号
    private List<Kf> TransInfo;

    public List<Kf> getTransInfo() {
        return TransInfo;
    }

    public void setTransInfo(List<Kf> transInfo) {
        TransInfo = transInfo;
    }
}
