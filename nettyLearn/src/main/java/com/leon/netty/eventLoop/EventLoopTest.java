package com.leon.netty.eventLoop;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class EventLoopTest {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(); // io处理, 事件处理, 定时处理


        // 事件处理
        group.next().execute(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("execute.");
        });

        // 事件处理
        group.next().submit(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("submit.");
        });

        // 定时任务
        group.next().scheduleAtFixedRate(()-> {
            log.info("schedule.");
        }, 0, 1, TimeUnit.SECONDS);


        log.info("main.");

    }

}
