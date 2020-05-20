package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
public class ExceptionController {

    /**
     * 抛出 RuntimeException 异常
     *
     * @return
     */
    @RequestMapping("/boot/hello")
    public @ResponseBody String hello() {
        int a = 10 / 0;
        return "hello, spring boot.";
    }

    /**
     * 抛出 IOException
     *
     * @return
     */
    @RequestMapping("/boot/hello2")
    public @ResponseBody String hello2() throws FileNotFoundException {

        FileInputStream is = new FileInputStream("");


        return "hello, spring boot.";
    }
}
