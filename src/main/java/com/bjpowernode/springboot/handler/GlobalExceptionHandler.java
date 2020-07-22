package com.bjpowernode.springboot.handler;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.handler.exception.CustomException;
import com.bjpowernode.springboot.common.utils.Response;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 也可以使用@RestControllerAdvice，它用于直接返回json的，不能返回jsp页面的
 */
@ControllerAdvice //可以用来返回统一的错误json或者是统一错误jsp页面
public class GlobalExceptionHandler {

    /**
     * @param ce 业务异常
     * @return
     * @ExceptionHandler相当于controller的@RequestMapping 如果抛出的的是CustomException，则调用该方法
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Response handle(CustomException ce) {
        return ResponseUtils.error(ce.getCode(), ce.getMessage());
    }


    /**
     * 当发生异常的时候，跳转到统一的错误页
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandlerByView(HttpServletRequest request, Exception e) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e.getMessage());
        modelAndView.addObject("url", request.getRequestURL());

        //设置发生异常的时候，跳转到哪个页面
        modelAndView.setViewName("50x");
        //可以返回统一数据
        return modelAndView;
    }

    /**
     * 处理404页面找不到错误
     * 配置一个bean
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            factory.addErrorPages(error404Page);
        });
    }
}