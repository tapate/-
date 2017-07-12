
package pers.zb.socket.message.userpush.hndler;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SystemWebSocketHandler implements WebSocketHandler {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final ArrayList<WebSocketSession> users;
    
    static {
        users = new ArrayList<WebSocketSession>();
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("websocket消息推送服务已连接.");
        users.add(session);
        String userName = session.getHandshakeHeaders().get("sec-websocket-key").get(0);
        if(userName!= null){
            session.sendMessage(new TextMessage("消息推送服务已连接."));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        TextMessage returnMessage = new TextMessage("服务器已经接收到您发送的消息，消息内容为：【 " + message.getPayload() + " 】.");
        logger.debug("客户端握手标识：" + session.getHandshakeHeaders().get("sec-websocket-key").get(0));
        session.sendMessage(returnMessage);
        logger.debug("returnMessage:" + returnMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable thrwbl) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.debug("websocket出现异常，连接关闭.");
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket连接关闭.");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            //if (user.getHandshakeHeaders().get("sec-websocket-key").get(0).equals(userName)) {
            
            //user.getUri().getQuery()  ---->  loginUserName=admin
            //具体的可以查看 /zb-ucenter-web/src/main/webapp/WEB-INF/view/socket/push_user.jsp 中的 connect() 函数，里面创建socket连接的时候带了用户名参数loginUserName
            //原理就是使用这个参数值与指定的用户名进行对比，来判断是不是需要发送消息给这个用户
            if(user.getUri().getQuery() != null && user.getUri().getQuery().split("=").length > 1){
                String[] uriData = user.getUri().getQuery().split("=");
                if (uriData[1].equals(userName)) {
                    try {
                        if (user.isOpen()) {
                            user.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
