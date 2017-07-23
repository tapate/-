package pers.zb.socket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import pers.zb.common.util.AjaxResult;
import pers.zb.socket.message.userpush.hndler.SystemWebSocketHandler;
import pers.zb.socket.notice.account.offline.handler.AccountOfflineNoticeWebSocketHandler;
import pers.zb.socket.notice.client.login.handler.ClientLoginNoticeWebSocketHandler;

@Controller
@RequestMapping("/socket")
public class SocketController {

    @Value("#{configProperties['service.url']}")
    private String SERVICE_URL;
    
    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
    
    @Bean
    public AccountOfflineNoticeWebSocketHandler accountOfflineNoticeWebSocketHandler() {
        return new AccountOfflineNoticeWebSocketHandler();
    }
    
    @Bean
    public ClientLoginNoticeWebSocketHandler clientLoginNoticeWebSocketHandler() {
        return new ClientLoginNoticeWebSocketHandler();
    }
    
    /**
     * 进入推送消息给指定用户的页面
     * @return
     */
    @RequestMapping("/userpush")
    public String userpush(ModelMap map) {
        map.put("serviceUrl", SERVICE_URL);
        return "socket/push_user";
    }
    
    /**
     * 进入推送消息给所有用户的页面
     * @param map
     * @return
     */
    @RequestMapping("/userpush/all")
    public String userpushAll(ModelMap map) {
        map.put("serviceUrl", SERVICE_URL);
        return "socket/push_user_all";
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
    public AjaxResult<String> toUser(HttpServletRequest request,
            String loginUserName,
            @RequestParam(name="message",required=true,defaultValue="这是服务器推送给您的消息.") String message){
        systemWebSocketHandler().sendMessageToUser(loginUserName, new TextMessage(message));
        return new AjaxResult<String>();
    }
    
    /**
     * 推送消息给所有人
     * @param request
     * @return
     */
    @RequestMapping("/pushAllUser")
    @ResponseBody
    public AjaxResult<String> toAllUser(HttpServletRequest request,
            @RequestParam(name="message",required=true,defaultValue="这是一条服务器推送给所有人的消息.") String message){
        systemWebSocketHandler().sendMessageToUsers(new TextMessage(message));
        return new AjaxResult<String>();
    }
    
    
    
    /**
     * 账户下线通知
     * @param request
     * @param loginUserName
     * @return
     */
    @RequestMapping("/accountOfflineNotice")
    @ResponseBody
    public AjaxResult<String> accountOfflineNotice(HttpServletRequest request,
            String loginUserName,
            @RequestParam(name="message",required=true,defaultValue="您的账号已在其他客户端登录") String message){
        accountOfflineNoticeWebSocketHandler().sendMessageToUser(loginUserName, new TextMessage(message));
        return new AjaxResult<String>();
    }
    
    /**
     * 通知所有在线用户，xx客户端登录了系统
     * @param request
     * @return
     */
    @RequestMapping("/clientLoginSystemNoticeNotice")
    @ResponseBody
    public AjaxResult<String> clientLoginSystemNoticeNotice(HttpServletRequest request){
        String message = "系统消息：有新的客户端登录了系统【IP：" + getIpAddr(request) + "】";
        clientLoginNoticeWebSocketHandler().sendMessageToAllUser(new TextMessage(message));
        return new AjaxResult<String>();
    }
    
    
    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }
        
        //这种情况下，是因为客户端使用了localhost访问项目，将localhos换成127.0.0.1就变成了IPV4
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }
}