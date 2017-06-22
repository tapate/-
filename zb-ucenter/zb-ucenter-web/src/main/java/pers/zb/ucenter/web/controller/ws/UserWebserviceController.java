package pers.zb.ucenter.web.controller.ws;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import pers.zb.common.util.util.AopTargetUtils;
import pers.zb.common.util.util.JsonUtil;
import pers.zb.common.util.util.ReflectionUtils;
import pers.zb.common.util.webservice.CommonResponse;
import pers.zb.common.util.webservice.IHandler;
import pers.zb.common.util.webservice.IRequest;
import pers.zb.common.util.webservice.IResponse;

/**
 * 用户相关接口
 *      这里的接口未在项目功能中使用到，只是作为测试学习使用。
 *      将接口封装成通用调用接口，采用泛型技术和抽象类，实现接口封装
 * 
 * @author zb
 *
 */
@Controller
@RequestMapping("/webservice/user")
public class UserWebserviceController {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 所有接口都请求该方法，通过反射获取具体实现类
     * 采用泛型、接口、抽象类等，进行代码封装。就不需要为每个接口写controller方法了。
     * 根据具体业务需求，采用合适的编码方式。
     * 
     * 对于抽象类、泛型等不是很明白的朋友，可以根据这个例子来研究学习。
     *      serviceCode：具体spring的bean名称，详见 UserQueryHandler.java 的注解名称
     * 
     * @param httpRequest
     * @param paramMap
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ResponseBody
    @RequestMapping(value = "/invoke",method = RequestMethod.POST)
    public IResponse invoke(HttpServletRequest httpRequest, @RequestBody Map<String,Object> paramMap) {
        LOGGER.debug(MessageFormat.format("接口参数:{0}", JsonUtil.toJson(paramMap)));
        try {
            //反射获得具体实现类和具体请求类
            IHandler handler  = getHandlerInstance(String.valueOf(paramMap.get("serviceCode")));
            Object obj = AopTargetUtils.getTarget(handler);
            Class c = ReflectionUtils.getInterfaceClassGenricType(obj.getClass(),0);
            IRequest request = (IRequest) c.newInstance();
            
            /**
             * 参数校验的操作，都交给每个接口的request接口类处理，完全解耦、抽象出来
             */
            List<String> checkParamList = request.checkParams();
            if(checkParamList != null && checkParamList.isEmpty() == false){
                //return setResponse(INTERFACE_CODE.NO_ENTITY.getValue(),checkParamList.toString());
            }
            
            //验证通过委托实现类调用接口
            IResponse response = handler.process(request);
            
            return response;
        } catch (Exception e) {
            
        }finally{
            
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public IHandler getHandlerInstance(String code) throws BeansException {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        IHandler handler = (IHandler) wac.getBean(code);
        return handler;
    }
    
    
    /**
     * 设置错误信息
     */
    public CommonResponse setResponse(int resCode,String resMsg) {
        CommonResponse response = new CommonResponse();
        response.setResultCode(resCode);
        response.setResultDesc(resMsg);
        return response;
    }
}
