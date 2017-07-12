package pers.zb.socket.monitor.tomcatlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import pers.zb.socket.monitor.tomcatlog.hndler.SystemWebSocketLogHandler;
import pers.zb.socket.monitor.tomcatlog.interceptor.HandshakeLogInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketLogConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    public WebSocketLogConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketLogHandler(), "/websck_log").addInterceptors(new HandshakeLogInterceptor());

        System.out.println("registed!");
        registry.addHandler(systemWebSocketLogHandler(), "/sockjs/websck_log").addInterceptors(new HandshakeLogInterceptor()).withSockJS();

    }

    @Bean
    public WebSocketHandler systemWebSocketLogHandler() {
        // return new InfoSocketEndPoint();
        return new SystemWebSocketLogHandler();
    }

}
