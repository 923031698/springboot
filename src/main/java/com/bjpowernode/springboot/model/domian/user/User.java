package com.bjpowernode.springboot.model.domian.user;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 直属上级
     */
    private Long managerId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 是否删除
     */
    private Integer deleted;

}