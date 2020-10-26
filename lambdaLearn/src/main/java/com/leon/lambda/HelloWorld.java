package com.leon.lambda;

import com.leon.api.vo.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelloWorld {

    public static void main(String[] args) {
        List<Student> students = Stream.of(new Student("张三", 18),
                new Student("李四", 20)).collect(Collectors.toList());
        System.out.println(students); // [Student(name=张三, age=18), Student(name=李四, age=20)]
        System.out.println(students.get(0).getName()); // 张三

        // filter
        List<Student> students2 = students.stream().filter(s -> s.getAge() == 18).collect(Collectors.toList());
        System.out.println(students2); // [Student(name=张三, age=18)]

        // map转换
        List<String> names = students.stream().map(s -> s.getName()).collect(Collectors.toList());
        System.out.println(names); //[张三, 李四]

        // flatMap将多个stream合并成一个stream
        List<Student> students3 = Stream.of(students, students2).flatMap(students1 -> students1.stream())
                .collect(Collectors.toList());
        System.out.println(students3); // [Student(name=张三, age=18), Student(name=李四, age=20), Student(name=张三, age=18)]

        // max和min 我们经常会在集合中求最大或最小值，使用流就很方便。及早求值。
        Optional<Student> max = students.stream().max(Comparator.comparing(s -> s.getAge()));
        Optional<Student> min = students.stream().min(Comparator.comparing(s -> s.getAge()));
        // 判断是否有值
        if (max.isPresent()) {
            System.out.println(max.get()); // Student(name=李四, age=20)
        }

        if (min.isPresent()) {
            System.out.println(min.get()); //Student(name=张三, age=18)
        }

        // count 统计功能，一般都是结合filter使用，因为先筛选出我们需要的再统计即可。及早求值
        long count = students3.stream().filter(s -> s.getAge() < 19).count();
        System.out.println(count); //2

        Set<Integer> studentSet = students3.stream().map(Student::getAge).collect(Collectors.toSet());
        System.out.println(studentSet);

    }
}
