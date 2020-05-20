package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.constants.Constant;
import com.bjpowernode.springboot.model.common.Response;
import com.bjpowernode.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/boot/register")
    public @ResponseBody
    String register(HttpSession session,
                    @RequestParam("phone") String phone,
                    @RequestParam("password") String password) {

        Response response = usersService.register(phone, password);

        if (response.getCode() == 0) {
            session.setAttribute(Constant.LOGIN_USER, response.getData());

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

        if (response.getCode() == 0) {
            session.setAttribute(Constant.LOGIN_USER, response.getData());

            return "<script>window.parent.uploadOK('OK')</script>";
        }
        return "<script>window.parent.uploadOK('NO')</script>";
    }

    @RequestMapping("/boot/logout")
    public String toUpload(HttpServletRequest request) {
        request.getSession().removeAttribute(Constant.LOGIN_USER);
        return "redirect:/";
    }
}
