package com.leon.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.CharsetEncoder;

@Slf4j
public class TestNettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        Bootstrap group = bootstrap.group(new NioEventLoopGroup());
        Bootstrap channel = group.channel(NioSocketChannel.class);
        Bootstrap handler = channel.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast();
            }
        });
        ChannelFuture sync = handler.connect("localhost",8080).sync();

    }
}
