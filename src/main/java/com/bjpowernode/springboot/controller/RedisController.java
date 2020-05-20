package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.service.RedisService;
import com.bjpowernode.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/boot/redis")
    public String redis() {
        usersService.show();
        redisService.cache();

        return "OK";
    }

    @RequestMapping("/boot/redis2")
    public String redis2() {
        redisService.cacheBySelf();

        return "OK";
    }
}
