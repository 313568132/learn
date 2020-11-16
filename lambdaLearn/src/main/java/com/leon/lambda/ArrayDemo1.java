package com.leon.lambda;

import com.leon.api.constant.ConstantArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayDemo1 {

    public static void main(String[] args) {
        String[] array = ConstantArray.ARRAY;
        List<String> list = ConstantArray.LIST;
        /**
         * 集合操作
         * 1. 查询以"l"开头的
         * 2. 转换为大写
         * 3. 排序
         */
        List<String> l = list.stream().filter(s -> s.startsWith("l"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(l);

        /**
         * 数组操作
         * 1. 查询以"l"开头的
         * 2. 转换为大写
         * 3. 排序
         */

        List<String> l2 =  Stream.of(array).filter(s -> s.startsWith("l"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(l2);

        /**
         * 文件操作
         * 1. 查询以"l"开头的
         * 2. 转换为大写
         * 3. 排序
         */
        try {
            Path path = Paths.get("C:\\Users\\Administrator\\Desktop\\text.txt");
            List<String> l3 = Files.lines(path).filter(s -> s.startsWith("l"))
                    .map(String::toUpperCase)
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(l3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
