package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.common.utils.Response;

public interface UsersService {

    Response register(String phone, String password);

    Response login(String phone, String password);
//
//    void show();

    void add();

    void update();

    Response select();
}