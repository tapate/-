package pers.zb.ucenter.web.chat.netty.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pers.zb.ucenter.web.chat.netty.server.ChatServer;

public class ChatServiceListener implements ServletContextListener {

    /**
     * netty启动 不能再主线程里面，不然会阻塞tomcat的启动，导致tomcat启动超时。
     * 
     * 可以在tomcat启动的时候写个listener  在listener里启动netty服务  
     * 不要在tomcat主线程启动 那样会锁死后面的操作  可以 new Thread() {  启动netty服务 }.start()
     * 
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("netty聊天服务监听器开始启动...");
        new Thread() {
            @Override
            public void run() {
                try {
                    
                    /**
                     * 启动聊天服务
                     */
                    new ChatServer().start();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.err.println("netty聊天服务监听器启动完成！");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
