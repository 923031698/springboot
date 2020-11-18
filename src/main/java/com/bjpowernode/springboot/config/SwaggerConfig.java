package com.bjpowernode.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xb
 * * @Description: 集成swagger
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * 在spring容器配置一个bean对象
     */
    @Bean
    public Docket createRestApi1(Environment environment) {
        //设置要显示的Swagger 环境
        Profiles profiles = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境单重
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).groupName("小号接口文档1")
                //是否启用swagger2
                .enable(flag)
                .select()
                //扫描有ApiOperation注解的方法
                //RequestHandlerSelectors 配置要扫描接口的形式
                //any():全部扫描
                //none():不扫描
                //withClassAnnotation :扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation :扫描方法上的注解
                //basePackage ：扫描指定的包
                .apis(RequestHandlerSelectors.basePackage("com.bjpowernode.springboot.controller"))
                //paths() :过滤
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 在spring容器配置一个bean对象
     */
    @Bean
    public Docket createRestApi(Environment environment) {
        //设置要显示的Swagger 环境
        Profiles profiles = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境单重
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).groupName("小号接口文档2")
                //是否启用swagger2
                .enable(flag)
                .select()
                //扫描有ApiOperation注解的方法
                //RequestHandlerSelectors 配置要扫描接口的形式
                //any():全部扫描
                //none():不扫描
                //withClassAnnotation :扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation :扫描方法上的注解
                //basePackage ：扫描指定的包
                .apis(RequestHandlerSelectors.basePackage("com.bjpowernode.springboot.service"))
                //paths() :过滤
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建api的基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("柏金标的接口文档")
                .description("模仿建立一个swagger2接口文档")
                .termsOfServiceUrl("http://www.bjpowernode.com/")
                .contact(new Contact("cat", "http://www.bjpowernode.com", "cat@163.com"))
                .license("采用 Apache 2.0 开源许可证")
                .licenseUrl("http://http://www.bjpowernode.com/licence.txt")
                .version("1.0.0")
                .build();
    }
}