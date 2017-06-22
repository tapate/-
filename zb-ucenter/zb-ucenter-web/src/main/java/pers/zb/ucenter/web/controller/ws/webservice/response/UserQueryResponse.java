package pers.zb.ucenter.web.controller.ws.webservice.response;

import pers.zb.common.util.webservice.AbstractResponse;
import pers.zb.common.util.webservice.IResponse;

public class UserQueryResponse extends AbstractResponse implements IResponse<UserQueryResponse> {
    private String realName; // 客户真实姓名
    private String mobile; // 手机号码
    private String email; // 电子邮箱

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
