package pers.zb.ucenter.web.controller.login;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.zb.common.util.enums.BasicConfigEnum;
import pers.zb.common.util.util.JsonUtil;
import pers.zb.entity.basic.BasicConfig;
import pers.zb.entity.basic.BasicSourceDownload;
import pers.zb.entity.sys.SysMenu;
import pers.zb.ucenter.rpc.api.basic.BasicConfigService;
import pers.zb.ucenter.rpc.api.basic.BasicSourceDownloadService;
import pers.zb.ucenter.rpc.api.sys.MenuService;

@Controller
@RequestMapping(value="")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MenuService menuService;

    @Autowired
    private BasicSourceDownloadService basicSourceDownloadService;
    
    @Autowired
    private BasicConfigService basicConfigService;
    
    @Value("#{configProperties['service.url']}")
    private String SERVICE_URL;
    
    /**
     * 进入首页
     * 
     * 创建日期：2016年8月2日 上午11:14:02 操作用户：zhoubang
     * 
     * @return
     */
    @RequestMapping(value="")
    public String page(HttpServletRequest request,ModelMap map) {
        Subject currentUser = SecurityUtils.getSubject();
        // 验证是否成功登录的方法
        if (currentUser.isAuthenticated()) {

            // 获取所有左侧父菜单
            List<SysMenu> parentMenuList = menuService.getAllParentList();
            for (SysMenu parentMenu : parentMenuList) {
                parentMenu.setHasMenu(true);

                // 获取子菜单
                List<SysMenu> subMenu = menuService.getSubMenuByParentId(parentMenu.getId());
                parentMenu.setSubMenu(subMenu);
            }
            String sessionId = request.getSession().getId();
            map.put("menuList", parentMenuList);
            map.put("serviceUrl", SERVICE_URL);
            
            
            Subject subject = SecurityUtils.getSubject();
            if(subject != null){
                map.put("userName", String.valueOf(subject.getPrincipal()));
            }
            
            /**
             * 1、实现的功能：同一个账户只能同时在一个终端登录，重复登录会剔除之前的用户。
             * 2、这里的sessionId的作用，是为了在首页创建socket连接的时候，作为用户名参数的后缀，用于区分相同登录账户的不同客户端。
             *      比如：new WebSocket("ws://域名/websck?loginUserName=帐户名_sessionId的值");
             */
            map.put("sessionId", sessionId);
            return "index";//跳转到index.jsp
        }
        return "login";
    }

    /**
     * /index 请求 ， 进入首页后的默认页面 
     * @return
     */
    @RequestMapping(value = "/main")
    public String defaultPage(HttpServletRequest request,ModelMap map) {
        String clientIp = getIpAddr(request);
        map.put("clientIp", clientIp);
        map.put("sourceCount", basicSourceDownloadService.getAllList().size());
        map.put("curTime", DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH时mm分ss秒"));
        
        
        BasicConfig basicConfig = basicConfigService.getConfigByName(BasicConfigEnum.project_debugging_tip.toString());
        if(basicConfig != null){
            logger.debug("是否开启网站在线调试提示信息:" + JsonUtil.toJson(basicConfig));
            map.put("projectDebuggingTip", basicConfig.getConfigValue());
        }
        return "main";
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

    /**
     * 进入项目源码下载页面
     * 
     * @return
     */
    @RequestMapping(value = "/sourceDownload")
    public String sourceDownload(ModelMap map) {
        List<BasicSourceDownload> sourceList = basicSourceDownloadService.getAllList();
        map.put("sourceList", sourceList);
        return "/down/sourceDownload";
    }
}
