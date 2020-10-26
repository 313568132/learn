package com.leon.springl.config;

import com.leon.springl.aop.LogAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigUser.class)
@EnableAspectJAutoProxy
public class ConfigAop {
    @Bean
    public LogAop logAop(){
        return new LogAop();
    }
}
