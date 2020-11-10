package com.bjpowernode.springboot.model.domian.user;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bjpowernode.springboot.common.enums.SexEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Users extends Model<Users> {

    private static final long serialVersionUID = -3691556073652514317L;
    private String id;
    private String nick;

    private String phone;

    private String password;

    private String email;

    private String account;

    private String name;

    private Integer age;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private SexEnum sex;


}