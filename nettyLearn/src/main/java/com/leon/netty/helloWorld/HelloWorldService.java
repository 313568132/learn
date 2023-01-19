package com.leon.netty.helloWorld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class HelloWorldService {

    public static void main(String[] args) {
        // 1. 启动器
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 2. enventLoop 组
        // ServerBootstrap group = bootstrap.group(new NioEventLoopGroup());
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        // boss 只负责 ServerSocketChannel 上的accept事件.  worker 只负责 ServerSocketChannel 上的读写事件.
        ServerBootstrap group = bootstrap.group(boss,worker);
        // 3. 选择器, 选择要使用的channle类型
        ServerBootstrap channel = group.channel(NioServerSocketChannel.class);
        // 4.
        ServerBootstrap handler = channel.childHandler(
                // 5.
                new ChannelInitializer<NioSocketChannel>() {
                    // 12. 建立连接后被调用
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("channelRead: {}", msg);
                                super.channelRead(ctx, msg);
                            }
                        });
                    }
                });
        // 6.
        handler.bind(8080);
    }

}
