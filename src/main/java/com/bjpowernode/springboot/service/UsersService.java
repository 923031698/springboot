package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.model.domian.user.Users;

public interface UsersService {

    Response register(String phone, String password);

    Response login(String phone, String password);

    void show();

    void add(Users users);

    void update();

    Response select();
}