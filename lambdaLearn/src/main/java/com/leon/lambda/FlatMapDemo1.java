package com.leon.lambda;

import com.leon.api.constant.ConstantArray;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapDemo1 {
    public static void main(String[] args) {
        List<String> list = ConstantArray.LIST;
        /**
         * 多维数组使用map打印的数组是对象
         */
        list.stream()
                .map(s -> s.split(""))
                .forEach(System.out::println);

        /**
         * 多维数组使用flatMap打印的是值
         */
        list.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .forEach(System.out::println);

        System.out.println("============================");
        /**
         * 多维数组使用flatMap打印的是值
         * Arrays.stream与Stream.of都能将数组转成流
         */
        list.stream()
                .flatMap(s -> Stream.of(s.split("")))
                .forEach(System.out::println);
    }
}
