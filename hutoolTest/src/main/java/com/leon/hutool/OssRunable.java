package com.leon.hutool;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class OssRunable implements Runnable{

    private OSS ossClient;
    private String objectName;
    private CountDownLatch latch;
    private String filePath;

    public OssRunable(OSS ossClient, String objectName, CountDownLatch latch,String filePath) {
        this.ossClient = ossClient;
        this.objectName = objectName;
        this.latch = latch;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "skuimage";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        // String objectName = "2019/2/b.jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        // String filePath= "C:\\Users\\Administrator\\Desktop\\a.jpg";

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));

        try {
            // 上传文件。
            long millis = System.currentTimeMillis();
            ossClient.putObject(putObjectRequest);
            long millis2 = System.currentTimeMillis();
            log.info("线程"+ Thread.currentThread().getName() +"执行结束. 执行时间: " + (millis2 - millis));
        } finally {
            latch.countDown();
        }
    }
}
