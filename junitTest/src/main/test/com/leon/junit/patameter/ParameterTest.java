package com.leon.junit.patameter;

import com.leon.junit.Caculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParameterTest {

    /**
     * 参数化测试
     * @return
     */
    @ParameterizedTest
    @CsvSource({"3,2,1","4,2,2","5,2,3"})
    public void add( int expected, int vone, int vtwo){
        Caculator caculator = new Caculator();
        Assertions.assertEquals(expected,caculator.add(vone,vtwo));
    }
}
