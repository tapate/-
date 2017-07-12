package pers.zb.socket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import pers.zb.common.util.AjaxResult;
import pers.zb.socket.message.userpush.hndler.SystemWebSocketHandler;

@Controller
@RequestMapping("/socket")
public class SocketController {

    @Value("#{configProperties['service.url']}")
    private String SERVICE_URL;
    
    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
    
    /**
     * 简单的消息服务器推送测试页面
     * @return
     */
    @RequestMapping("/userpush")
    public String userpush(ModelMap map) {
        map.put("serviceUrl", SERVICE_URL);
        return "socket/push_user";
    }
    
    /**
     * 进入tomcat的log日志实时监控页面
     * @return
     */
    @RequestMapping("/tomcatlog")
    public String tomcatlog(ModelMap map) {
        map.put("serviceUrl", SERVICE_URL);
        return "socket/push_tomcat_log";
    }
    
    /**
     * 推送消息给指定的人
     * @param request
     * @param loginUserName
     * @return
     */
    @RequestMapping("/pushUser")
    @ResponseBody
    public AjaxResult<String> toUser(HttpServletRequest request,String loginUserName) {
        systemWebSocketHandler().sendMessageToUser(loginUserName, new TextMessage("这是服务器推送给您的消息。"));
        return new AjaxResult<String>();
    }
    
    /**
     * 推送消息给所有人
     * @param request
     * @return
     */
    @RequestMapping("/pushAllUser")
    @ResponseBody
    public AjaxResult<String> toAllUser(HttpServletRequest request) {
        systemWebSocketHandler().sendMessageToUsers(new TextMessage("这是一条服务器推送给所有人的消息。"));
        return new AjaxResult<String>();
    }
}