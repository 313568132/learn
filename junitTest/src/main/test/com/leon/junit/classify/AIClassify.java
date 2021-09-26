package com.leon.junit.classify;

import com.alibaba.excel.EasyExcel;
import com.leon.excel.listener.ExcelDemoListener;
import com.leon.excel.vo.ExcelDemo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class AIClassify {

    /**
     * @BeforeClass 必须是public类型并且是static
     */
    @BeforeAll
    public static void initClass() {
        System.out.println("initClass()");
    }

    public static String desc = "Welcome to our store :)\n" +
            "Please allow 1-2cm manual measurement error, please understand. Thank you!\n" +
            "Hot Sale Trainers Men Breathable Running Shoes Comfortable Sneakers Fashion Shoes Sports Shoes Jogging Shoes Walking Shoes Tennis Shoes";

    @Test
    public void test() {
        String replace = desc.replaceAll("[\\W]"," ");
        System.out.println(replace);
        System.out.println(replace.toLowerCase().contains(" understand "));
        System.out.println("test()");
    }


    @Test
    public void test2() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\新建 Microsoft Excel 工作表 (4).xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelDemo.class, new ExcelDemoListener()).sheet().doRead();
    }

    @Test
    public void esq(){
        Map<String,Map<String,Map<String,Object>>> mapss = new HashMap<>();
        Map<String,Map<String,Object>> maps = new HashMap<>();
        Map<String,Map<String,Object>> maps2 = new HashMap<>();
        Map<String,Map<String,Object>> maps3 = new HashMap<>();
        Map<String,Map<String,Object>> maps4 = new HashMap<>();

        Map<String,Object> map = new HashMap<>();
        map.put("country","CN");
        map.put("mode","B2B");
        Map<String,Object> map2 = new HashMap<>();
        map2.put("descSource","desccription");
        Map<String,Object> map3 = new HashMap<>();
        Map<String,Object> map4 = new HashMap<>();
        maps.put("term",map);
        maps2.put("match",map2);
        /*maps3.put("term",map3);
        maps4.put("term",map4);*/
        mapss.put("filter",maps);
        mapss.put("must",maps2);
        mapss.put("must_not",maps3);
        mapss.put("should",maps4);

        System.out.println(mapss);
    }

}
