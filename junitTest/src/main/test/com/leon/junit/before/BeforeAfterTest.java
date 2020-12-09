package com.leon.junit.before;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BeforeAfterTest {

    @Test
    public void test(){
        System.out.println("test()");
    }

    @Test
    public void test2(){
        System.out.println("test2()");
    }

    /**
     * @Before 必须是public类型的
     */
    @BeforeEach
    public void init(){
        System.out.println("init()");
    }

    /**
     * @BeforeClass 必须是public类型并且是static
     */
    @BeforeAll
    public static void initClass(){
        System.out.println("initClass()");
    }

    /**
     * @After 必须是public类型的
     */
    @AfterEach
    public void distory(){
        System.out.println("distory()");
    }

    /**
     * @AfterClass 必须是public类型并且是static
     */
    @AfterAll
    public static void distoryClass(){
        System.out.println("distoryClass()");
    }


}
