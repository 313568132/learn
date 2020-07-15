package com.leon.junit.patameter;

import com.leon.junit.Caculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ParameterTest {
    private int expected;
    private int vone;
    private int vtwo;

    /**
     * 参数化测试
     * @return
     */
    @Parameterized.Parameters
    public static Collection<Integer[]> getTestParameters(){
        return Arrays.asList(new Integer[][]{
                {3,2,1},
                {4,2,2},
                {5,2,3}
        });
    }

    public ParameterTest(int expected, int vone, int vtwo){
        this.expected = expected;
        this.vone = vone;
        this.vtwo = vtwo;
    }

    @Test
    public void add(){
        Caculator caculator = new Caculator();
        Assert.assertEquals(expected,caculator.add(vone,vtwo),0);
    }
}
