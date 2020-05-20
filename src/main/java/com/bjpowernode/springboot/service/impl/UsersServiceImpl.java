package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.constants.Constant;
import com.bjpowernode.springboot.mapper.users.UsersMapper;
import com.bjpowernode.springboot.model.common.Response;
import com.bjpowernode.springboot.model.user.Users;
import com.bjpowernode.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 注入即可使用
     */
    @Autowired
    private RedisTemplate redisTemplate;

    public Response register(String phone, String password) {
        Users users = new Users();
        users.setPhone(phone);
        users.setPassword(password);
        int insert = usersMapper.insertSelective(users);
        if (insert > 0) {
            return new Response(Constant.ZERO, "注册成功", users);
        } else {
            return new Response(Constant.ONE, "注册失败");
        }
    }

    public Response login(String phone, String password) {
        Users users = usersMapper.login(phone, password);
        if (users != null) {
            return new Response(Constant.ZERO, "登录成功", users);
        } else {
            return new Response(Constant.ONE, "账号或密码不匹配");
        }
    }

    public void show() {

        System.out.println("222.............");
        redisTemplate.opsForValue().set("abc", "abcdefg");

        System.out.println("userservice show方法执行了.............");
    }
}