package com.leon.lambda;

import com.leon.api.constant.ConstantArray;
import com.leon.api.vo.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllDemo {

    @Test
    public void testArray(){
        String[] array = ConstantArray.ARRAY;
        List<String> list = ConstantArray.LIST;
        /**
         * 集合操作
         * 1. 查询以"l"开头的
         * 2. 转换为大写
         * 3. 排序
         */
        List<String> l = list.stream().filter(s -> s.startsWith("l"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(l);

        /**
         * 数组操作
         * 1. 查询以"l"开头的
         * 2. 转换为大写
         * 3. 排序
         */

        List<String> l2 =  Stream.of(array).filter(s -> s.startsWith("l"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(l2);

        /**
         * 文件操作
         * 1. 查询以"l"开头的
         * 2. 转换为大写
         * 3. 排序
         */
        try {
            Path path = Paths.get("C:\\Users\\Administrator\\Desktop\\text.txt");
            List<String> l3 = Files.lines(path).filter(s -> s.startsWith("l"))
                    .map(String::toUpperCase)
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(l3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFilter(){
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

    @Test
    public void testFlatMap(){
        List<String> list = ConstantArray.LIST;
        /**
         * 多维数组使用map打印的数组是对象
         */
        list.stream()
                .map(s -> s.split(""))
                .forEach(System.out::println);

        /**
         * 多维数组使用flatMap打印的是值
         */
        list.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .forEach(System.out::println);

        System.out.println("============================");
        /**
         * 多维数组使用flatMap打印的是值
         * Arrays.stream与Stream.of都能将数组转成流
         */
        list.stream()
                .flatMap(s -> Stream.of(s.split("")))
                .forEach(System.out::println);
    }
    @Test
    public void testMap(){
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

        /**
         * list转为map,多个key相同时
         */
        Map<Integer, Student> map = students.stream()
                .collect(Collectors.toMap(Student::getAge, Student -> Student,(key1,key2)->key2));
        System.out.println(map);

        /**
         * list转为map, 有相同的key时会报错: java.lang.IllegalStateException:Duplicate key
         */
        Map<Integer, String> map2 = students.stream()
                .collect(Collectors.toMap(Student::getAge, Student::getName));
        System.out.println(map2);
    }
    @Test
    public void testPrint(){
        PrintDemo1 demo1 = new PrintDemo1();
        // 不用lambda表达式
        demo1.printSomething("name", new PrintDemo1.Print() {
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
    @Test
    public void testReduce(){
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
    @Test
    public void testSql(){
        List<String> list = ConstantArray.LIST;
        List<String> limit = list.stream()
                .limit(2).collect(Collectors.toList());
        System.out.println(limit);

        List<String> distinct = list.stream()
                .distinct().collect(Collectors.toList());
        System.out.println(distinct);

        List<String> skip = list.stream()
                .skip(2).collect(Collectors.toList());
        System.out.println(skip);

        List<String> sorted = list.stream()
                .sorted().collect(Collectors.toList());
        System.out.println(sorted);
    }
}
