package com.leon.junit;

public class CaculatorTest {
    public static void main(String[] args) {
        try {
            Caculator caculator = new Caculator();
            int add = caculator.add(0, 3);
            System.out.println(add);
            int a = 1/0;
        } catch (Exception e) {
            // throw new IllegalArgumentException(e);
            throw new IllegalStateException(e);
        }
    }
}
