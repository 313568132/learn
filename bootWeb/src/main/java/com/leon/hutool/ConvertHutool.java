package com.leon.hutool;

import org.apache.commons.lang3.StringUtils;

public class ConvertHutool {

    public static void main(String[] args) {
        String replace = StringUtils.replace("Y929，Y930,Y900,Y942", "，", ",");
        System.out.println(replace);

        String replace2 = StringUtils.replace(null, "，", ",");
        System.out.println(replace2);

        String replace3 = StringUtils.replace("", "，", ",");
        System.out.println(replace3);
    }
}
