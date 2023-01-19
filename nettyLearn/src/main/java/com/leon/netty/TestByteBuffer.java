package com.leon.netty;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {

        try {
            FileChannel channel = new FileInputStream("nettyLearn/test.txt").getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(10);


            while (true){
                // channel向buffer里写入数据
                int read = channel.read(byteBuffer);
                // 当read为-1时, 说明buffer中没有数据了
                if(read == -1){
                    break;
                }
                // flip方法, 转换buffer的读写方式. (默认为写模式)
                byteBuffer.flip();

                // hasRemaining判断buffer中是否还有数据
                while (byteBuffer.hasRemaining()){
                    log.info("数据: {}", (char) byteBuffer.get());
                }
                // 恢复buffer为写模式
                byteBuffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
