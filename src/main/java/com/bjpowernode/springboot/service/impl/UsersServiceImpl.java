package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.constants.Constant;
import com.bjpowernode.springboot.mapper.users.UsersMapper;
import com.bjpowernode.springboot.model.ResultObject;
import com.bjpowernode.springboot.model.Users;
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

    public ResultObject register(String phone, String password) {
        Users users = new Users();
        users.setPhone(phone);
        users.setPassword(password);
        int insert = usersMapper.insertSelective(users);
        if (insert > 0) {
            return new ResultObject(Constant.ZERO, "注册成功", users);
        } else {
            return new ResultObject(Constant.ONE, "注册失败");
        }
    }

    public ResultObject login(String phone, String password) {
        Users users = usersMapper.login(phone, password);
        if (users != null) {
            return new ResultObject(Constant.ZERO, "登录成功", users);
        } else {
            return new ResultObject(Constant.ONE, "账号或密码不匹配");
        }
    }

    public void show() {

        System.out.println("222.............");
        redisTemplate.opsForValue().set("abc", "abcdefg");

        System.out.println("userservice show方法执行了.............");
    }
}