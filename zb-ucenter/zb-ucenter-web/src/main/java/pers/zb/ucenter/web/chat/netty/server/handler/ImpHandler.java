package pers.zb.ucenter.web.chat.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.zb.ucenter.web.chat.netty.processor.MsgProcessor;
import pers.zb.ucenter.web.chat.netty.protocol.IMMessage;

/**
 * 处理自定义协议的逻辑
 */
public class ImpHandler extends SimpleChannelInboundHandler<IMMessage> {

    MsgProcessor processor = new MsgProcessor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMMessage msg) throws Exception {
        processor.sendMsg(ctx.channel(), msg);
    }

}
