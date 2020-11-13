package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.enums.SexEnum;
import com.bjpowernode.springboot.model.domian.user.Users;
import com.bjpowernode.springboot.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试类")
@RestController
public class SwaggerController {


    @Autowired
    UsersService usersService;

    /**
     * https://www.jianshu.com/p/f30e0c646c63  swagger 简书教程
     *
     * @param id     用户id
     * @param status 用户状态
     * @return Users  用户返回信息
     */
    @ApiOperation(value = "根据用户id查询用户信息")
    @GetMapping(value = "/swagger/{id}/{status}")
    public Users getUserInfo(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        Users users = new Users();
        users.setNick("cat");
        users.setPhone("1370000000");
        users.setPassword("******");
        users.setEmail("cat@163.com");
        users.setAccount("NO68958886878664");
        return users;
    }


    @ApiOperation(value = "添加用户")
    @GetMapping(value = "/swagger/insert")
    public Users insert() {
        Users users = new Users();
        users.setNick("cat");
        users.setPhone("1370000000");
        users.setPassword("******");
        users.setEmail("cat@163.com");
        users.setAccount("NO68958886878664");
        users.setSex(SexEnum.MAN);
        return users;
    }


    @ApiOperation(value = "添加用户")
    @GetMapping(value = "/swagger/insert2")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public Users insert2(@RequestBody Users user) {
        Users users = new Users();
        users.setNick("cat");
        users.setPhone("1370000000");
        users.setPassword("******");
        users.setEmail("cat@163.com");
        users.setAccount("NO68958886878664");
        users.setSex(SexEnum.MAN);
        return users;
    }
}