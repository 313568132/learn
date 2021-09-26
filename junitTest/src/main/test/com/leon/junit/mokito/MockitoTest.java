package com.leon.junit.mokito;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

public class MockitoTest {

    //验证行为
    @Test
    public void verifyBehaviour(){
        //模拟创建一个List对象
        List mock = mock(List.class);
        //使用mock的对象
        mock.add(1);
        mock.clear();
        //验证add(1)和clear()行为是否发生
        verify(mock).add(1);
        verify(mock).clear();
        //Mockito.verify(mock).add(2);
    }

    //模拟我们期待的结果
    @Test
    public void whenThenReturn(){
        //mock一个Iterator
        Iterator iterator = mock(Iterator.class);
        // 预设当iterator调用next时, 第一次返回hello, 第二次返回world, 第n次返回my.
        when(iterator.next()).thenReturn("hello").thenReturn("world").thenReturn("my");
        //
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next() + " " + iterator.next();
        // 验证
        assertEquals("hello world my my",result);
    }

    @Test
    public void whenThenException() {
        // Executable closureContainingCodeToTest = () -> throw new IllegalArgumentException("a message");
        // assertThrows(IllegalArgumentException.class, closureContainingCodeToTest, "a message");
        assertThrows(RuntimeException.class,()-> {
           throw new RuntimeException("this is a run time exception test");
        });
    }

    @Test
    public void returnSmartNullsTest() {
        // mock一个List
        List list = mock(List.class,RETURNS_SMART_NULLS);
        // List list = mock(List.class);
        System.out.println(list.get(0));

        System.out.println(list.toArray().length);
    }

}
