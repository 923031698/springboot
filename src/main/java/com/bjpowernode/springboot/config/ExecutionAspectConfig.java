package com.bjpowernode.springboot.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.stereotype.Component;

/**
 * @Author: bjb
 * @Description: AOP使用
 * @Date: 2020-09-09 20:08:26
 */
@Aspect
@Component
public class ExecutionAspectConfig {
    @Pointcut("execution(public * com.bjpowernode.springboot.service.impl..*Service*.*(..))")
    public void matchCondition(){}

    @Before("matchCondition()")
    public void before(){
        System.out.println(213123);
        System.out.println("前置通知。。。。。");
        System.out.println("baijinbiao");
        System.out.println("baijinbiao ");
        System.out.println("###before");
    }


}

