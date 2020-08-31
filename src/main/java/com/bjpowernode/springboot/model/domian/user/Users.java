package com.bjpowernode.springboot.model.domian.user;



import com.bjpowernode.springboot.common.utils.DistributedGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class Users  implements Serializable {

    private static final long serialVersionUID = -3691556073652514317L;

    @Id
    @KeySql(genId = DistributedGenId.class)
    private String id;

    private String nick;

    private String phone;

    private String password;

    private String email;

    private String account;


}