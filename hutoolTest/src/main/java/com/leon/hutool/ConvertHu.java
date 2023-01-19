package com.leon.hutool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ConvertHu {
    @Test
    public void toStr(){
        Integer number = 10;
        String s = Convert.toStr(number);
        System.out.println(s);
        Integer number2 = null;
        String s2 = Convert.toStr(number2);
        System.out.println(s2);
        String str = "4";
        Integer strInt = Convert.toInt(str);
        System.out.println(strInt);

        String strs = "s";
        Integer strInts = Convert.toInt(strs);
        System.out.println(strInts);

        Integer toInt = Convert.toInt(strs, 0);
        System.out.println(toInt);

        boolean numeric = StrUtil.isNumeric("4.5");
        System.out.println(numeric);

        StrUtil.isNotEmpty("");
        StrUtil.isNotBlank("");
        StrUtil.isBlank("");
        Convert.toDate("");


        // HttpUtil.get("");
        // HttpUtil.isHttp()

    }

    @Test
    public void cache(){
        LRUCache<Object, Object> objects = CacheUtil.newLRUCache(1000);
        objects.put("a","b",1);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(objects.get("a"));
    }

    @Test
    public void systemJvm(){
        System.out.println(SystemUtil.getJvmSpecInfo());
        System.out.println(SystemUtil.getJvmInfo());
        System.out.println(SystemUtil.getJavaSpecInfo());
        System.out.println(SystemUtil.getJavaInfo());
        System.out.println(SystemUtil.getJavaRuntimeInfo());
        System.out.println(SystemUtil.getOsInfo());
        System.out.println(SystemUtil.getUserInfo());
        System.out.println(SystemUtil.getHostInfo());
        System.out.println(SystemUtil.getRuntimeInfo());

    }
}
