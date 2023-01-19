package com.leon.hutool;

import cn.hutool.core.lang.Console;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TempTest {

    public static String urlSp = "http://54.67.16.180:8089/rest/run/3c280acf4bfb403f917a666730b3ef98";
    public static void main(String[] args) {
        Gson gson = new Gson();
        Map<String,String> map = Maps.newHashMap();
        List<String> dyUrlList = Lists.newArrayList(
                "https://shop.tiktok.com/view/product/1729550254609173045?region=GB",
                "https://shop.tiktok.com/view/product/1729550254609173045?region=GB",
                "https://shop.tiktok.com/view/product/1729550426019891243?region=GB",
                "https://shop.tiktok.com/view/product/1729492221640411401?region=GB",
                "https://shop.tiktok.com/view/product/1729474331434321983?region=GB",
                "https://shop.tiktok.com/view/product/1729550424348854671?region=MY",
                "https://shop.tiktok.com/view/product/1729545496085039761?region=GB",
                "https://shop.tiktok.com/view/product/1729486013828401855?region=PH",
                "https://shop.tiktok.com/view/product/1729543127157934728?region=VN",
                "https://shop.tiktok.com/view/product/1729506830793280257?region=GB",
                "https://shop.tiktok.com/view/product/1729471739305691796?region=MY");

        ExecutorService executorService = executorFatory();
        int num = 5;
        final CountDownLatch cdl = new CountDownLatch(num);
        ConcurrentMap<Integer, Object> concurrentMap = Maps.newConcurrentMap();

        for (int i = 0; i < num; i++) {
            final int it = i;
            // try {
                executorService.execute(() -> {
                    try {
                        // Console.log("线程: {}", Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(it%10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        /*String url = dyUrlList.get(it);
                        map.put("href",url);
                        String body = gson.toJson(map);
                        String post = HttpUtil.post(urlSp, body, 1000 * 60);*/
                        // int x = 1/0;
                        if(it%10 == 0){
                            int x = 1/0;
                        }
                        concurrentMap.put(it, "ok: " + Thread.currentThread().getName());
                    } catch (Exception e){
                        concurrentMap.put(it, "no: " + Thread.currentThread().getName());
                    }finally {
                        // Console.log("线程: {}, 执行结束. 执行数据 {} . ", Thread.currentThread().getName(),it);
                        // 子线程调用cdl.countDown(),一直有效
                        cdl.countDown();
                    }
                });
            /*} catch (Exception e) {
                // 保留try catch, 主线程new ThreadPoolExecutor.CallerRunsPolicy()异常调用cdl.countDown()失败时才有效.
                Console.log(e);
            }*/
        }

        executorService.shutdown();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Console.log(concurrentMap);
        Console.log("方法运行完成.");
    }


    public static ExecutorService executorFatory() {
        ExecutorService executor = new ThreadPoolExecutor(1,
                2, 20,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
