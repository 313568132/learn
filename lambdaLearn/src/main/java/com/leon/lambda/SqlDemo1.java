package com.leon.lambda;

import com.leon.api.constant.ConstantArray;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlDemo1 {

    public static void main(String[] args) {
        List<String> list = ConstantArray.LIST;
        List<String> limit = list.stream()
                .limit(2).collect(Collectors.toList());
        System.out.println(limit);

        List<String> distinct = list.stream()
                .distinct().collect(Collectors.toList());
        System.out.println(distinct);

        List<String> skip = list.stream()
                .skip(2).collect(Collectors.toList());
        System.out.println(skip);

        List<String> sorted = list.stream()
                .sorted().collect(Collectors.toList());
        System.out.println(sorted);
    }
}
