package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response;

import java.io.Serializable;

/**
 * 客服MODEL类
 */
public class Kf implements Serializable {

    private static final long serialVersionUID = 4885317410831260203L;

    // 客服帐号
    private String KfAccount;

    public String getKfAccount() {
        return KfAccount;
    }

    public void setKfAccount(String kfAccount) {
        KfAccount = kfAccount;
    }
}
