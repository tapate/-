
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
        logger.debug("connect to the websocket success......");
        users.add(session);
        String userName = session.getHandshakeHeaders().get("sec-websocket-key").get(0);
        if(userName!= null){
            session.sendMessage(new TextMessage("消息推送服务已连接"));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        TextMessage returnMessage = new TextMessage("服务器已经接收到您发送的消息，消息内容为：[" + message.getPayload() + "]");
        logger.debug("客户端握手标识：" + session.getHandshakeHeaders().get("sec-websocket-key").get(0));
        session.sendMessage(returnMessage);
        logger.debug("returnMessage:" + returnMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable thrwbl) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
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
            if (user.getUri().getQuery().split("=")[1].equals(userName)) {
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
