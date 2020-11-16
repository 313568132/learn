package com.leon.lambda;

import com.leon.api.vo.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapDemo1 {
    public static void main(String[] args) {
        Student student = new Student("xiao",11);
        Student student2 = new Student("liu",12);
        Student student3 = new Student("tong",13);
        Student student5 = new Student("yi",14);
        Student student4 = new Student("xue",13);
        List<Student> students = Arrays.asList(student, student2, student3, student4, student5);

        /**
         * 修改对象中的元素
         * 给每位同学年龄加1
         */
        List<Student> collect = students.stream()
                .map(s -> {
                    s.setAge(s.getAge() + 1);
                    return s;
                }).collect(Collectors.toList());
        System.out.println(collect);

        /**
         * 修改对象中的元素
         * 1. 给每位同学年龄加1
         * 2. 如果姓名是: :"xue", 改成: "xuedi"
         * 使用流打印
         */
        students.stream()
                .map(s -> {
                    s.setAge(s.getAge() + 1);
                    String xue = s.getName().equals("xue") ? "xuedi" : s.getName();
                    s.setName(xue);
                    return s;
                }).forEach(System.out::println);

        /**
         * 修改对象中的元素
         * 使用peek可以省略return;
         * 1. 给每位同学年龄加1
         * 2. 如果姓名是: :"xue", 改成: "xuedi"
         * 使用流打印
         */
        List<Student> xue1 = students.stream()
                .peek(s -> {
                    s.setAge(s.getAge() + 1);
                    String xue = s.getName().equals("xue") ? "xuedi" : s.getName();
                    s.setName(xue);
                }).collect(Collectors.toList());
        System.out.println(xue1);
    }
}
