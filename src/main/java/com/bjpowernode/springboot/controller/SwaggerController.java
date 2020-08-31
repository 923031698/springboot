package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.model.domian.user.Users;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("springboot使用swagger测试")
@RestController
public class SwaggerController {

    @ApiOperation(value = "获取用户信息", notes = "根据id来获取用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID",  dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "status", value = "用户状态", dataType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "缺少必要的请求参数"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/swagger/{id}/{status}",method = RequestMethod.GET)
    public Users getUserInfo(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        Users users = new Users();
        users.setNick("cat");
        users.setPhone("1370000000");
        users.setPassword("******");
        users.setEmail("cat@163.com");
        users.setAccount("NO68958886878664");
        return users;
    }
}