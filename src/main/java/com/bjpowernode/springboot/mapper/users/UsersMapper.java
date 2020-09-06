package com.bjpowernode.springboot.mapper.users;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.springboot.model.domian.user.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper extends BaseMapper<Users> {
    Users selectByUserId(@Param("userId") String userId);
}