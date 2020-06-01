package com.bjpowernode.springboot.model.user;



import lombok.Data;

import java.io.Serializable;

@Data
public class Users  implements Serializable {

    private static final long serialVersionUID = -3691556073652514317L;
    private Integer id;

    private String nick;

    private String phone;

    private String password;

    private String email;

    private String account;


}