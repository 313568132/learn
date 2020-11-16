package com.leon.lambda;

import com.leon.api.constant.ConstantArray;
import com.leon.api.vo.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReduceDemo1 {
    public static void main(String[] args) {

        List<Integer> listInt = ConstantArray.LIST_INT;

        Student student = new Student("xiao",11);
        Student student2 = new Student("liu",12);
        Student student3 = new Student("tong",13);
        Student student5 = new Student("yi",14);
        Student student4 = new Student("xue",13);
        List<Student> students = Arrays.asList(student, student2, student3, student4, student5);

        /**
         * 规约操作
         * 把map(Student::getAge)取出来进行操作
         * reduce(0): 第一个值是初始值
         * subTotal: 累加值
         * index: 集合中的当前值
         */
        Integer reduce = students.stream()
                .map(Student::getAge)
                .reduce(0, (subTotal,age)->subTotal + age);
        System.out.println(reduce);

        /**
         * 规约操作
         * 把map(Student::getAge)取出来进行操作
         * reduce(0): 第一个值是初始值
         * subTotal: 累加值
         * index: 集合中的当前值
         */
        Integer reduce2 = students.stream()
                .map(Student::getAge)
                .reduce(0, Integer::sum);
        System.out.println(reduce2);

        /**
         * 规约操作
         * 把map(Student::getAge)取出来进行操作
         * reduce(0): 第一个值是初始值
         * subTotal: 累加值
         * std: 集合中的当前值(对象,没有经过map转)
         * Integer::sum 合并操作
         */
        Integer reduce5 = students.stream()
                .reduce(0, (subTotal,std)-> subTotal + std.getAge(),Integer::sum);
        System.out.println(reduce5);

        Integer reduce3 = listInt.stream().reduce(0, (subTotal, value) -> subTotal + value);
        System.out.println(reduce3);

        Integer reduce4 = listInt.stream().reduce(0, Integer::sum);
        System.out.println(reduce4);

    }
}
