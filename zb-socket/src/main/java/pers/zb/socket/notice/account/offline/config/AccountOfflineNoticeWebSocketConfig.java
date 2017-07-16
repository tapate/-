package pers.zb.socket.notice.account.offline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import pers.zb.socket.message.userpush.interceptor.HandshakeInterceptor;
import pers.zb.socket.notice.account.offline.handler.AccountOfflineNoticeWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class AccountOfflineNoticeWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    public AccountOfflineNoticeWebSocketConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(accountOfflineNoticeWebSocketHandler(), "/websck_notice_offline").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(accountOfflineNoticeWebSocketHandler(), "/sockjs/websck_notice_offline").addInterceptors(new HandshakeInterceptor()).withSockJS();

    }

    @Bean
    public WebSocketHandler accountOfflineNoticeWebSocketHandler() {
        return new AccountOfflineNoticeWebSocketHandler();
    }

}
