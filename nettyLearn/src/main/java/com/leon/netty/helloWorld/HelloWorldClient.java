package com.leon.netty.helloWorld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class HelloWorldClient {
    public static void main(String[] args) throws InterruptedException {
        // 7.
        Bootstrap bootstrap = new Bootstrap();
        // 8.
        Bootstrap group = bootstrap.group(new NioEventLoopGroup());
        // 9.
        Bootstrap channel1 = group.channel(NioSocketChannel.class);
        // 10.
        Bootstrap handler = channel1.handler(new ChannelInitializer<NioSocketChannel>() {
            // 12. 建立连接后被调用
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                // 16. 添加编码处理器
                ch.pipeline().addLast(new StringEncoder());
            }
        });
        // 11.
        ChannelFuture connect = handler.connect(new InetSocketAddress("localhost", 8080));
        // 13. 阻塞方法, 直到建立连接
        ChannelFuture sync = connect.sync();
        // 14. 获取链接channel
        Channel channel = sync.channel();
        // 15. 发送数据
        channel.writeAndFlush("hello2");

        AtomicInteger integer = new AtomicInteger();
        integer.getAndIncrement();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
