package com.leon.springl.aop;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LogAop {

    @Pointcut("execution(* com.leon.springl.service.UserService.*(..))")
    public void logPoint(){}

    @Around("logPoint()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        try {
            log.info("访问方法 -----> : {}.{},开始.",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
            Object[] args = joinPoint.getArgs();
            List<Object> list = new ArrayList<>();
            for (int i = 0; i <  args.length; i++){
                if(!(args[i] instanceof ServletRequest) && !(args[i] instanceof ServletResponse)){
                    list.add(args[i]);
                }
            }
            log.info("方法 -----> : {}, 访问参数: {}.",joinPoint.getSignature().getName(),JSON.toJSONString(list));
            proceed = joinPoint.proceed();
            log.info("方法 -----> : {}, 返回数据: {}.",joinPoint.getSignature().getName(),JSON.toJSONString(proceed));
            log.info("访问方法 -----> : {}.{},结束.",joinPoint.getSignature().getDeclaringType().getName(),joinPoint.getSignature().getName());
        } catch (Exception e) {
            log.error("系统异常 -----> : {}", e);
            throw e;
        } catch (Throwable throwable) {
            log.error("系统错误 -----> : {}", throwable);
            throw throwable;
        }
        return proceed;
    }

    @Pointcut("execution(* com.leon.springl.service.AnimalService.*(..))")
    public void allPoint(){}

    @Before("allPoint()")
    public void aopBefore(){
        System.out.println("LogAop aopBefore()......");
    }

    @After("allPoint()")
    public void aopAfter(){
        System.out.println("LogAop aopAfter()......");
    }

    @AfterReturning(pointcut = "allPoint()",returning = "o")
    public void aopAfterReturning(Object o){
        System.out.println("LogAop aopAfterReturning()......" + o.toString());
    }

    @AfterThrowing(pointcut = "allPoint()",throwing = "e")
    public void aopAfterReturning(Exception e){
        System.out.println("LogAop aopAfterReturning()......" + e);
    }
}
