package com.bjpowernode.springboot.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //开启Swagger2的支持
@Configuration
public class SwaggerConfig {

    /**
     * 在spring容器配置一个bean对象
     *
     * @Bean等价于 <bean id="createRestApi" class="xxxx.Docket">
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//扫描有ApiOperation注解的方法
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建api的基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("集成Swagger2构建RESTful APIs")
                .description("集成Swagger2构建RESTful APIs")
                .termsOfServiceUrl("http://www.bjpowernode.com/")
                .contact(new Contact("cat","http://www.bjpowernode.com","cat@163.com"))
                .license("采用 Apache 2.0 开源许可证")
                .licenseUrl("http://http://www.bjpowernode.com/licence.txt")
                .version("1.0.0")
                .build();
    }
}