package com.leon.lambda;

import com.leon.api.vo.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilterDeom1 {

    public static void main(String[] args) {

        Student student = new Student("xiao",11);
        Student student2 = new Student("liu",12);
        Student student3 = new Student("tong",13);
        Student student5 = new Student("yi",14);
        Student student4 = new Student("xue",13);
        List<Student> students = Arrays.asList(student, student2, student3, student4, student5);

        /**
         * 排序
         */
        /*students.sort(Comparator.comparing(Student::getAge).reversed());
        students.forEach(System.out::println);*/

        /**
         * filter 单条件
         */
        List<Student> collect = students.stream()
                .filter(s -> s.getAge() > 12)
                .collect(Collectors.toList());
        System.out.println(collect);

        /**
         * filter 多条件
         */
        List<Student> collect2 = students.stream()
                .filter(s -> s.getAge() > 12 && s.getName().equals("yi"))
                .collect(Collectors.toList());
        System.out.println(collect2);

        /**
         * 查找第一个大于10的学生
         */
        Optional<Student> optional = students.stream().filter(s -> s.getAge() > 10).findFirst();
        System.out.println(optional.get());

        /**
         * 查找第一个大于100的学生
         * 如果没有查询导值:Exception in thread "main" java.util.NoSuchElementException: No value present
         */
        Optional<Student> optional2 = students.stream().filter(s -> s.getAge() > 100).findFirst();
        // System.out.println(optional2.get());

        /**
         * 查找是否有大于10的学生
         */
        boolean present = students.stream().filter(s -> s.getAge() > 10).findFirst().isPresent();
        System.out.println(present);

        /**
         * 查找是否有大于10的学生, 存在时执行ifPresent的内容
         */
        students.stream().filter(s -> s.getAge() > 10).findFirst().ifPresent(x -> System.out.println(x));

        /**
         * 查找是否有大于10的学生, 如果不存在执行orElse的内容
         */
        Student student1 = students.stream().filter(s -> s.getAge() > 100).findFirst().orElse(new Student("jia", 19));
        System.out.println(student1);

    }
}
