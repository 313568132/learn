package com.leon.junit.before;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BeforeAfterTest {

    @Test
    public void test(){
        System.out.println("test()");
    }

    /**
     * @Before 必须是public类型的
     */
    @Before
    public void init(){
        System.out.println("init()");
    }

    /**
     * @BeforeClass 必须是public类型并且是static
     */
    @BeforeClass
    public static void initClass(){
        System.out.println("initClass()");
    }

    /**
     * @After 必须是public类型的
     */
    @After
    public void distory(){
        System.out.println("distory()");
    }

    /**
     * @AfterClass 必须是public类型并且是static
     */
    @AfterClass
    public static void distoryClass(){
        System.out.println("distoryClass()");
    }


}
