package com.leon;

public class Test3 {

    public static void main(String[] args) {
        int i = 10;
        System.out.println(test(i));
    }

    private static int test(int i) {

        try {
            i = i + 20;
            return i;
        }finally {
            int s = 1;
            i = i + 30;
            // return i;
        }
    }
}
