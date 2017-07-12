package pers.zb.socket.monitor.tomcatlog.hndler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class TailLogThread extends Thread {

    private BufferedReader reader;
    private WebSocketSession session;

    public TailLogThread(InputStream in, WebSocketSession session) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.session = session;

    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                // 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
                TextMessage message = new TextMessage((line).getBytes());
                session.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}