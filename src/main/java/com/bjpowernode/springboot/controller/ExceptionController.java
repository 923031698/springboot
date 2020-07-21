package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExceptionController {

    @Autowired
    RedisService redisService;





    /**
     * 抛出 RuntimeException 异常
     *
     * @return
     */
    @RequestMapping("/boot/hello")
    public Response hello() {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        return ResponseUtils.success(list);
    }

    /**
     * 抛出 IOException
     *
     * @return
     */
    @RequestMapping("/boot/hello2")
    public String hello2() throws FileNotFoundException {

        FileInputStream is = new FileInputStream("");


        return "hello, spring boot.";
    }
}
