package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.common.enums.ErrorTypeEnum;
import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.handler.exception.CustomException;
import com.bjpowernode.springboot.mapper.users.UsersMapper;
import com.bjpowernode.springboot.model.domian.user.Users;
import com.bjpowernode.springboot.service.RedisService;
import com.bjpowernode.springboot.service.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j2
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;


    /**
     * 注入即可使用
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;

    public Response register(String phone, String password) {
        Users users = new Users();
        users.setPhone(phone);
        users.setPassword(password);
        int insert = usersMapper.insert(users);
        if (insert == 0) {
            throw new CustomException(ErrorTypeEnum.ADD_FAILURE);
        }
        return ResponseUtils.success();
    }

    public Response login(String phone, String password) {
      /*  Users users1 = new Users();
        users1.setPhone(phone);
        users1.setPassword(password);
        Users users = usersMapper.se(users1);
        if (users == null) {
            throw new CustomException(ErrorTypeEnum.QUERY_FAILURE);
        }*/
        return ResponseUtils.success();
    }

    public void show() {

        System.out.println("222.............");
        redisTemplate.opsForValue().set("abc", "abcdefg");

        System.out.println("userservice show方法执行了.............");
    }

    @Override
    public void add(Users users) {
        
    }

    /**
     * 新增
     */
    public void add() {
        Users user = new Users();
        user.setPassword("123123");
        user.setPhone("18667039325");
        user.setEmail("923031698@qq.com");
        user.setAccount("123123123");
        usersMapper.insert(user);
    }

    public void update() {

    }

    public Response select() {
        List<Users> users = usersMapper.selectList(null);
        return ResponseUtils.success(users);
    }


}