package com.leon.springl.aop;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LogAop {

    @Pointcut("execution(* com.leon.springl.service.*.*(..))")
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
}
