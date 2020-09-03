package com.bjpowernode.springboot;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bjpowernode.springboot.mapper.users.UsersMapper;
import com.bjpowernode.springboot.model.domian.user.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    UsersMapper usersMapper;


    /**
     * 根据主键id查询   对象
     */
    @Test
    public void selectById() {
        Users users = usersMapper.selectById("1301343945864032258");
        System.out.println(users);
    }


    /**
     * 根据主键id集合查询  返回集合
     */
    @Test
    public void selectIds() {
        List<Users> list = usersMapper.selectBatchIds(Arrays.asList("1301343945864032258", "1301343614660886530"));
        list.forEach(System.out::println);
    }

    /**
     * 根据条件查询 方式1 返回集合
     */
    @Test
    public void selectByMap() {
        Map map = new HashMap<String, Object>();
        map.put("phone", "18667039325");
        map.put("id", "1301343614660886530");
        List<Users> list = usersMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    /**
     * 查询所有不带条件 返回集合
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Users> userList = usersMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    /**
     * 插入不会插入空值属性，单会插入空字符串属性
     */
    @Test
    public void testInsert() {
        Users users = new Users();
        users.setAccount("柏金标账号");
        users.setEmail("923031698@qq.com");
        users.setPassword("123");
        users.setNick("哈哈");
        users.setPhone("18667039325");
        users.setName("柏金标");
        users.setAge(40);
        usersMapper.insert(users);
    }


    /**
     * 修改不会插入空值属性，单会插入空字符串属性
     */
    @Test
    public void update() {
        Users users = new Users();
        users.setId("1301343945864032258");
        users.setName("柏金标");
        users.setNick(null);
        users.setNick("");
        usersMapper.updateById(users);
    }


    /**
     * 1.名字中包含雨并且年龄小于40岁
     * name  like  "%雨%" and age<40
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        //QueryWrapper<Users> query = Wrappers.<Users>query();
        queryWrapper.like("name", "雨").lt("age", 40);

        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 2.名字中包含雨并且年龄大于等于20且小于等于40并且email 不等于空
     * name  like  "%雨%"    and  between 20  and 40 and email is not null
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        //QueryWrapper<Users> query = Wrappers.<Users>query();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 3.名字为王姓 或者年龄大于等于25,按照年龄降序排列 ，年龄相同按照id升序排列
     * name  like  "王%"    or  age>=40 order by age desc,id asc
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        //QueryWrapper<Users> query = Wrappers.<Users>query();
        queryWrapper.likeLeft("name", "王").or().ge("age", 40).orderByDesc("age").orderByAsc("id");
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

}
