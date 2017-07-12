package pers.zb.socket.message.userpush.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import pers.zb.socket.message.userpush.hndler.SystemWebSocketHandler;
import pers.zb.socket.message.userpush.interceptor.HandshakeInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    public WebSocketConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(), "/websck").addInterceptors(new HandshakeInterceptor());

        System.out.println("registed!");
        registry.addHandler(systemWebSocketHandler(), "/sockjs/websck").addInterceptors(new HandshakeInterceptor()).withSockJS();

    }

    @Bean
    public WebSocketHandler systemWebSocketHandler() {
        // return new InfoSocketEndPoint();
        return new SystemWebSocketHandler();
    }

}
