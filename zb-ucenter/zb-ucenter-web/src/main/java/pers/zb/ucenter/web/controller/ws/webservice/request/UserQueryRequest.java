package pers.zb.ucenter.web.controller.ws.webservice.request;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import pers.zb.common.util.webservice.AbstractRequest;
import pers.zb.common.util.webservice.IRequest;

/**
 * 获取用户信息，接口请求类
 * 
 * @author zb
 *
 */
public class UserQueryRequest extends AbstractRequest implements IRequest<UserQueryRequest> {

    public static final String[] PARAM_NAMES = new String[] { "mobile" };

    private String mobile; // 手机号码

    /**
     * 校验参数合法性
     */
    @Override
    public List<String> checkParams() {
        return null;
    }

    /**
     * 获取所有请求参数，包含公共的参数，比如sign、timestamp等，要根据具体业务接口来定义
     */
    @Override
    public String[] getParamNames() {
        return (String[]) ArrayUtils.addAll(super.getParamNames(), PARAM_NAMES);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
