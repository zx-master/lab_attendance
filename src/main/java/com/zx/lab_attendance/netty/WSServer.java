package com.zx.lab_attendance.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/2 0:07
 * @Description
 */
@Component
public class WSServer {

    private static class SingletionWSServer {
        static final WSServer INSTANCE = new WSServer();
    }

    public static WSServer getInstance() {
        return SingletionWSServer.INSTANCE;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WSServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    public void start(){
        this.future = server.bind(8088);
        System.err.println("netty websocket 启动完毕");
    }

}
