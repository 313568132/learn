package com.leon.springl;

import com.leon.springl.service.UserService;
import com.leon.springl.serviceConfig.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppApplication {

    /**
     * 测试:
     *  @Autowired
     *  @PostConstruct
     *  @PreDestroy
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        StudentService studentService = (StudentService) context.getBean("studentService");
        studentService.ssay();
    }
}
