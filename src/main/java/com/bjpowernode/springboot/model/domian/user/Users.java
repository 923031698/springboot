package com.bjpowernode.springboot.model.domian.user;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = -3691556073652514317L;

    @TableId("id")
    private String id;

    private String nick;

    private String phone;

    private String password;

    private String email;

    private String account;


}