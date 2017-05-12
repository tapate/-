package pers.zb.ucenter.web.chat.netty.client;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import pers.zb.ucenter.web.chat.netty.client.handler.ChatClientHandler;
import pers.zb.ucenter.web.chat.netty.protocol.IMDecoder;
import pers.zb.ucenter.web.chat.netty.protocol.IMEncoder;

public class ChatClient {

    private String host;
    private int port;
    private ChatClientHandler clientHandler;

    public ChatClient(String nickName) {
        this.clientHandler = new ChatClientHandler(nickName);
    }

    public void connect(String host, int port) {
        this.host = host;
        this.port = port;

        // 一个工作进程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {

                    // MyEclipse里面的纯Socket请求
                    // 既不可能发送HTTP请求，也不可能发送WebScoket请求
                    // 这里我们只发自定义协议相关的请求
                    ch.pipeline().addLast(new IMDecoder());
                    ch.pipeline().addLast(new IMEncoder());
                    ch.pipeline().addLast(clientHandler);

                }
            });
            ChannelFuture f = b.connect(this.host, this.port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatClient("小周").connect("127.0.0.1", 8888);
    }
}