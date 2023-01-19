package com.leon.hutool;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.List;


public class HttpTest {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.stream().parallel().forEach(System.out::println);







        String url = "http://192.168.103.13:8094/musesBackend/commodityApi/queryCommodityCn";
        String url2 = "http://47.74.223.175:8095/musesBackend/commodityApi/queryCommodityCn";
        String body = "[{\"orderCode\":\"ECKLT00000332296\",\"merchantSku\":\"ÉTOILE\",\"merchantId\":2035,\"clearanceCountry\":\"JP\",\"clearanceMode\":\"B2B\"}]";
        HttpRequest post = HttpRequest.post(url);
        post.body(body);
        HttpResponse execute = post.execute();
        String result = execute.body();
        System.out.println(result);

        System.out.println("-------------------------------分割线--------------------------------------");

        String result2 = HttpUtil.post(url2, body);
        System.out.println(result2);
    }
}
