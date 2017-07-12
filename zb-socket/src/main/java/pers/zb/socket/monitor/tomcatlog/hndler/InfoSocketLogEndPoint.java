package pers.zb.socket.monitor.tomcatlog.hndler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class InfoSocketLogEndPoint extends TextWebSocketHandler {

    public InfoSocketLogEndPoint() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // TODO Auto-generated method stub
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
        session.sendMessage(returnMessage);
    }

    /*
     * @Override protected void handleBinaryMessage(WebSocketSession arg0,
     * BinaryMessage arg1) { // TODO Auto-generated method stub
     * super.handleBinaryMessage(arg0, arg1); }
     */

}
