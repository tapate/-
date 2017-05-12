package pers.zb.ucenter.web.controller.chat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 用户管理
 * 
 * 创建日期：2016年8月13日 下午4:07:01
 * 操作用户：zhoubang
 *
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 进入聊天页面
     * @description 
     * 
     * @author zhoubang 
     * @date 2017年3月17日 下午4:53:08 
     * 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/toChatView")
    public String toChatView(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
        return "/chat/netty/chat";
    }
}
