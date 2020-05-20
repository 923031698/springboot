package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.ResultObject;

public interface UsersService {

    public ResultObject register(String phone, String password);

    public ResultObject login(String phone, String password);

    public void show();
}