package com.bjpowernode.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 设置动态数据源需要，禁用数据源自动配置
@EnableTransactionManagement//开启springBoot事务
@MapperScan("com.bjpowernode.springboot.mapper.users")
@EnableCaching//开启基于注解的缓存
public class WebApplication {

    public static void main(String[] args) {

        //启动spring容器 + 启动一个内嵌的Tomcat
        SpringApplication.run(WebApplication.class, args);
    }

}