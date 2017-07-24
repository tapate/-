
package pers.zb.socket.notice.client.login.handler;

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
public class ClientLoginNoticeWebSocketHandler implements WebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final ArrayList<WebSocketSession> users;

    static {
        users = new ArrayList<WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("websocket消息推送服务已连接.");
        users.add(session);

        /*
         * String userName =
         * session.getHandshakeHeaders().get("sec-websocket-key").get(0);
         * if(userName!= null){ session.sendMessage(new TextMessage(
         * "开始监听 [账户下线] 通知服务...")); }
         */
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
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
     * 通知所有用户：xx客户端登陆了系统
     *
     * @param message
     */
    public void sendMessageToAllUser(TextMessage message) {
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
}
