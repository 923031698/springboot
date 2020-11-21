package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.model.domian.user.Users;
import com.bjpowernode.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/boot/register")
    public String register(HttpSession session,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password) {

        Response response = usersService.register(phone, password);

        if (response.getCode() == 0) {
            //    session.setAttribute(Constant.LOGIN_USER, response.getData());

            return "<script>window.parent.uploadOK('OK')</script>";
        }
        return "<script>window.parent.uploadOK('NO')</script>";
    }

    @RequestMapping("/boot/login")
    public @ResponseBody
    String login(HttpSession session,
                 @RequestParam("phone") String phone,
                 @RequestParam("password") String password) {

        Response response = usersService.login(phone, password);

        if (response.getCode() == 200) {
            //    session.setAttribute(Constant.LOGIN_USER, response.getData());

            return "<script>window.parent.uploadOK('OK')</script>";
        }
        return "<script>window.parent.uploadOK('NO')</script>";
    }

    @RequestMapping("/boot/logout")
    public String toUpload(HttpServletRequest request) {
        //   request.getSession().removeAttribute(Constant.LOGIN_USER);
        return "redirect:/";
    }

    @RequestMapping("/boot/add")
    public Response add(Users users) {
        usersService.add(users);
        return ResponseUtils.success();
    }

    @RequestMapping("/boot/update")
    public Response update() {
        usersService.update();
        return ResponseUtils.success();
    }


    @RequestMapping("/boot/select")
    public Response select() {
        Response response = usersService.select();
        return response;
    }

}
