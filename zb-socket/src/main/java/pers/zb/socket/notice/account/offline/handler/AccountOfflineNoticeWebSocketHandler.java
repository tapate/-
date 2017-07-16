
package pers.zb.socket.notice.account.offline.handler;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class AccountOfflineNoticeWebSocketHandler implements WebSocketHandler {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final ArrayList<WebSocketSession> users;
    
    static {
        users = new ArrayList<WebSocketSession>();
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("websocket消息推送服务已连接.");
        users.add(session);
        
        
        /*String userName = session.getHandshakeHeaders().get("sec-websocket-key").get(0);
        if(userName!= null){
            session.sendMessage(new TextMessage("开始监听 [账户下线] 通知服务..."));
        }*/
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        TextMessage returnMessage = new TextMessage("[账户下线]：服务器已经接收到您发送的消息，消息内容为 【 " + message.getPayload() + " 】.");
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
     * 给相同账号在不同终端登录的客户端推送下线通知
     *  比如admin账号在A、B两个客户端登陆，则在C客户端登陆的时候，需要将A、B客户端账户会话通知下线
     *
     * @param userName 例如：zhangsan_xxxxxxxxxxxxxxxxxxxxxxxx
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        if(StringUtils.isNoneBlank(userName)){
            for (WebSocketSession user : users) {
                //if (user.getHandshakeHeaders().get("sec-websocket-key").get(0).equals(userName)) {
                
                if(user.getUri().getQuery() != null && user.getUri().getQuery().split("=").length > 1){
                    String[] uriData = user.getUri().getQuery().split("=");
                    
                    //uriData[1]的参数值，类似于：zhangsan_xxxxxxxxx，在前端创建socket连接的时候指定的。
                    //userName值类似于zhangsan_xxxxxxxxxxxxxx，后面部分是sessionId，所以，不同客户端sessionId不同，只能根据用户名来模糊匹配
                    //System.out.println(uriData[1]);
                    if (uriData[1].contains(userName.split("_")[0]) && !userName.equals(uriData[1])) {//自己的话就不推送消息
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
        }
    }
}
