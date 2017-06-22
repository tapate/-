package pers.zb.ucenter.web.controller.ws.webservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pers.zb.common.util.util.JsonUtil;
import pers.zb.common.util.webservice.AbstractHandler;
import pers.zb.common.util.webservice.IHandler;
import pers.zb.ucenter.web.controller.ws.webservice.request.UserQueryRequest;
import pers.zb.ucenter.web.controller.ws.webservice.response.UserQueryResponse;

/**
 * 具体的接口实现
 *      注解中的值 userquery，就是接口请求参数serviceCode锁需要传递的值，用于java获取具体实现类
 * 
 * @author zb
 *
 */
@Component("userquery")
public class UserQueryHandler extends AbstractHandler implements IHandler<UserQueryRequest, UserQueryResponse> {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    /**
     * 查询用户信息接口
     *      process 通用的处理方法实现。每个接口都实现IHandler这个接口
     */
    @Override
    public UserQueryResponse process(UserQueryRequest request) {
        LOGGER.debug("查询用户信息接口，request：" + JsonUtil.toJson(request));
        UserQueryResponse response = new UserQueryResponse();
        response.setEmail("842324724@qq.com");
        response.setMobile("15988888888");
        response.setRealName("超级男人");
        response.setResultCode(200);
        response.setResultDesc("请求成功");
        return response;
    }

}
