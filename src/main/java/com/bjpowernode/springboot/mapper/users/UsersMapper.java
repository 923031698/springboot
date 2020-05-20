package com.bjpowernode.springboot.mapper.users;

import com.bjpowernode.springboot.model.user.Users;
import org.apache.ibatis.annotations.Param;

//@Mapper //标记为mybatis的mapper接口
public interface UsersMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users login(@Param("phone") String phone, @Param("password") String password);
}