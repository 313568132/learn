package com.leon.hutool;

import com.alibaba.fastjson.JSON;

public class TestEnum {
    public static void main(String[] args) {

        System.out.println(SeasonEnum.SPRING.equals(SeasonEnum.SPRING));
        System.out.println(SeasonEnum.SPRING);

        System.out.println(JSON.toJSONString(SeasonEnum.SUMMER));

        switch (SeasonEnum.SPRING){
            case SPRING:
                System.out.println("spring");
                break;
            case SUMMER:
                System.out.println("summer");
                break;
            case AUTUMN:
                System.out.println("autumn");
                break;
        }
    }
}
