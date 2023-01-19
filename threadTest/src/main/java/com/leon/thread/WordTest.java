package com.leon.thread;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class
WordTest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a","b");
        String s = JSONObject.toJSONString(strings);
        System.out.println(s);
    }
    public static void getText(String text){

    }
    public static void metnod(String words){
        /**
         * 思路: 这些command都是对字符串的操作,
         *      只要把字符串, key和value记录下来.就可以随意操作.
         *       在java中, 记录key和value使用map.
         * 1. 判断每行开头command, 根据command执行相应的命令
         * 2. set时: 记录key和value.
         * 3. append时: 组合字符串
         * 4. print时: 打印相应key的value.
         * 等
         */
        //读取
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\desc_src.txt"),"UTF-8"));
            StringBuffer sb = new StringBuffer();
            String line = null;
            int i = 0 ;
            BufferedWriter br2 = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\desc_cn.txt"));
            while((line= br.readLine()) != null) {
                if (line.length() > 0){
                    // 格式为: set key "value"
                    String[] s = line.split(" ");

                    switch (s[0]){
                        case "set":
                            getText(s[0]);
                            break;
                        case "append":
                            getText(s[0]);
                            break;
                        case "list":

                            break;
                        default:
                            break;
                    }
                }else{

                }
            }

            //写入
            br2.write(sb.toString());
            br2.flush(); //刷新缓冲区的数据到文件
            br.close();
            br2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
