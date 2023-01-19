package com.leon.netty.closeEvent;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.net.InetSocketAddress;
import java.util.Scanner;

@Slf4j
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        // 7.
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        // 8.
        Bootstrap group = bootstrap.group(loopGroup);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (true){
                    String next = scanner.next();

                    if("q".equals(next)){
                        log.info("channel.close();");
                        channel.close();
                        break;
                    }
                    channel.writeAndFlush(next);
                }
            }
        }).start();

        // 方式1 , 在main方法中关闭
//        ChannelFuture future = channel.closeFuture();
//        future.sync();
//        loopGroup.shutdownGracefully();
//        log.info("关闭");

        // 方式2 , 在Listener中关闭
        ChannelFuture future = channel.closeFuture();
        future.addListener(new ChannelFutureListener() {
            // 该方法会在channel.close()完成后触发
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                loopGroup.shutdownGracefully();
                log.info("loopGroup.shutdownGracefully();");
            }
        });
    }
}
