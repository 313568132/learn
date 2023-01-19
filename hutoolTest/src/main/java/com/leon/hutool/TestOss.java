package com.leon.hutool;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class TestOss {

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            list.add(i);
        }

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");


        long millis = System.currentTimeMillis();
        Instant.now();
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-ap-southeast-1.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "";
//        String accessKeySecret = "";
        String accessKeyId = "QCMl1TnlRMyjx6j7";
        String accessKeySecret = "tB4FJ1y6GCjd8ZypCL4VUEsBg3vwk2";
        String filePath= "C:\\Users\\Administrator\\Desktop\\a.jpg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        long millis2 = System.currentTimeMillis();
        log.info("方法OSSClientBuilder执行时间: {}毫秒.",(millis2 - millis));

        try {


            CountDownLatch latch = new CountDownLatch(10);
            for (int i = 0; i < 10 ; i++){
                new Thread(new OssRunable(ossClient, "2019/2/c"+ i +".jpg",
                        latch,filePath)).start();
            }
            latch.await();
            long millis3 = System.currentTimeMillis();
            log.info("方法一执行时间: {}毫秒.",(millis3 - millis2));

            log.info("-----------------------分割线-------------------------");


            list.parallelStream().forEach(s -> {
                long millis6 = System.currentTimeMillis();
                method(ossClient, s);
                long millis7 = System.currentTimeMillis();
                log.info("线程parallelStream"+ s +"执行结束. 执行时间: " + (millis7 - millis6));
            });

            list.stream().parallel().forEach(
                    s -> {
                        long millis6 = System.currentTimeMillis();
                        method(ossClient, s);
                        long millis7 = System.currentTimeMillis();
                        log.info("线程"+ s +"执行结束. 执行时间: " + (millis7 - millis6));
                    }
            );
            long millis4 = System.currentTimeMillis();
            log.info("方法二执行时间: {}毫秒.",(millis4 - millis3));
        } catch (OSSException oe) {
            System.out.println("Error Message:" + oe.getErrorMessage());
        } catch (ClientException ce) {
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        long millis5 = System.currentTimeMillis();
        log.info("总执行时间: {}毫秒.",(millis5 - millis));
    }
    public static void method(OSS ossClient, Integer s){
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "skuimage";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "2019/2/b" + s + ".jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath= "C:\\Users\\Administrator\\Desktop\\a.jpg";

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));

        // 上传文件。
        ossClient.putObject(putObjectRequest);
    }
}