package com.bjpowernode.springboot.model.domian.user;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bjpowernode.springboot.common.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xb
 */
@ApiModel(description = "用户实体类")
@Data
public class Users extends Model<Users> {

    private static final long serialVersionUID = -3691556073652514317L;

    @ApiModelProperty(value = "用户id")
    private String id;

    private String nick;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @ApiModelProperty(value = "性别")
    private SexEnum sex;


}