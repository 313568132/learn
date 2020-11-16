package com.leon.lambda;

public class PrintDemo1 {

    interface Print{
        public void print(String name);
    }

    void printSomething(String name,Print print){
        print.print(name);
    }

    public static void main(String[] args) {
        PrintDemo1 demo1 = new PrintDemo1();
        // 不用lambda表达式
        demo1.printSomething("name", new Print() {
            @Override
            public void print(String name) {
                System.out.println(name);
            }
        });

        // String类型的参数
        demo1.printSomething("String类型的参数", (String val)->{
            System.out.println(val);
        });

        // 一个参数时,可以省略参数类型
        demo1.printSomething("一个参数时,可以省略参数类型", (val)->{
            System.out.println(val);
        });

        // 一个参数时, 可以省略括号();
        demo1.printSomething("一个参数时, 可以省略括号", val->{
            System.out.println(val);
        });

        // 内容只有一句话时,可以省略花括号{};
        demo1.printSomething("内容只有一句话时,可以省略花括号{};", val-> System.out.println(val));
    }

}
