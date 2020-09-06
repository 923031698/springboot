package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.model.domian.user.Users;
import com.bjpowernode.springboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: bjb
 * @Description: //TODO
 * @Date: 2020-05-26 20:56:23
 */
@RestController
public class RedisController {

    @Autowired
    RedisService redisService;


    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    @RequestMapping("/redis/set")
    Response set( String key, String value) {
        key ="2";
        Users users = new Users();
        users.setPassword("123");
        users.setPhone("123123");

        Users users2 = new Users();
        users2.setPassword("123");
        users2.setPhone("123123");
        List<Users> list  =new ArrayList<>();
        list.add(users);
        list.add(users2);
        return ResponseUtils.success(redisService.set(key, list,100L, TimeUnit.SECONDS));
    }

    /**
     * 写入缓存
     *
     * @param key   键
     * @return boolean
     */
    @RequestMapping("/redis/get")
    Response get( String key) {
        Users o = (Users)redisService.get(key);
        return ResponseUtils.success(o);
    }
    /**
     * 写入缓存
     *
     * @param key   键
     * @return boolean
     */
    @RequestMapping("/redis/get2")
    Response get2( String key) {
        List<Users> o = (List<Users>)redisService.get(key);
        return ResponseUtils.success(o);
    }
}
