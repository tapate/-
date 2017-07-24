package pers.zb.socket.notice.client.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import pers.zb.socket.message.userpush.interceptor.HandshakeInterceptor;
import pers.zb.socket.notice.client.login.handler.ClientLoginNoticeWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class ClientLoginNoticeWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    public ClientLoginNoticeWebSocketConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(ClientLoginNoticeWebSocketHandler(), "/websck_notice_client_login").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(ClientLoginNoticeWebSocketHandler(), "/sockjs/websck_notice_client_login").addInterceptors(new HandshakeInterceptor()).withSockJS();

    }

    @Bean
    public WebSocketHandler ClientLoginNoticeWebSocketHandler() {
        return new ClientLoginNoticeWebSocketHandler();
    }

}
