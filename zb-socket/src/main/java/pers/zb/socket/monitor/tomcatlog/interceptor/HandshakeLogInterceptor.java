package pers.zb.socket.monitor.tomcatlog.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Component
public class HandshakeLogInterceptor extends HttpSessionHandshakeInterceptor {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        
        // 解决The extension [x-webkit-deflate-frame] is not supported问题
        if (request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
        }
        // return true;
        
        logger.debug("Before Handshake......");
        
        /*if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(true);
            if (session != null) {
                ((ServletServerHttpRequest) request).getServletRequest().getSession();
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute(USERNAME);
                attributes.put(USERNAME,userName);
            }
        }*/
        //return true;
        
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        logger.debug("After Handshake......");
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
