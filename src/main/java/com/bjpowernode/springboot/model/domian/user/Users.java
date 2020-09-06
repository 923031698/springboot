package com.bjpowernode.springboot.model.domian.user;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Users implements Serializable {

    private static final long serialVersionUID = -3691556073652514317L;

    private String id;

    private String nick;

    private String phone;

    private String password;

    private String email;

    private String account;

    private String name;

    private Integer age;

    private LocalDateTime createTime;


}