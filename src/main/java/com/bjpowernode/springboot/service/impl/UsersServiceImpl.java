package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.common.enums.ErrorTypeEnum;
import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.handler.exception.CustomException;
import com.bjpowernode.springboot.mapper.users.UsersMapper;
import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.model.domian.user.Users;
import com.bjpowernode.springboot.service.RedisService;
import com.bjpowernode.springboot.service.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;


@Log4j2
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 注入即可使用
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;

    public Response register(String phone, String password) {
        Users users = new Users();
        users.setPhone(phone);
        users.setPassword(password);
        int insert = usersMapper.insert(users);
        if (insert == 0) {
            throw new CustomException(ErrorTypeEnum.ADD_FAILURE);
        }
        return ResponseUtils.success();
    }

    public Response login(String phone, String password) {
        Users users1 = new Users();
        users1.setPhone(phone);
        users1.setPassword(password);
        Users users = usersMapper.selectOne(users1);
        if (users == null) {
            throw new CustomException(ErrorTypeEnum.QUERY_FAILURE);
        }
        return ResponseUtils.success();
    }

    public void show() {

        System.out.println("222.............");
        redisTemplate.opsForValue().set("abc", "abcdefg");

        System.out.println("userservice show方法执行了.............");
    }

    public void add() {
        Users users = new Users();
        users.setPassword("123456");
        users.setPhone("110");
        users.setAccount(redisService.incrSerial("DD"));
        usersMapper.insertSelective(users);
    }

    public void update() {
        Users users = usersMapper.selectByPrimaryKey("5f17af00ef90d443c93e067b");
        users.setNick("123213");
        users.setPassword("123456");
        users.setPhone("110");
        users.setAccount("12345");
        usersMapper.updateByPrimaryKeySelective(users);
    }

    public Response select() {
        String stu = "123";
        Weekend weekend = new Weekend(Users.class);
        WeekendCriteria<Users, Object> weekendCriteria = weekend.weekendCriteria();
        weekendCriteria.andEqualTo(Users::getPhone, "18667039325");
        weekendCriteria.andEqualTo(Users::getAccount, "DD2020072300007");
        List<Users> users = usersMapper.selectByExample(weekend);

        List<Users> usersList = usersMapper.selectByExample(new Example.Builder(Users.class)
                .where(WeekendSqls.<Users>custom()
                        .andEqualTo(Users::getPhone, "18667039325")
                        .andEqualTo(Users::getAccount, "DD2020072300007")).build());


        log.info("21312312313");


        return ResponseUtils.success(users);
    }


}