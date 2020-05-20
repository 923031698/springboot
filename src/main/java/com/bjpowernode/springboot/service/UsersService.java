package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.common.Response;

public interface UsersService {

    public Response register(String phone, String password);

    public Response login(String phone, String password);

    public void show();
}