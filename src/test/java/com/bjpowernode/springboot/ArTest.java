package com.bjpowernode.springboot;

import com.bjpowernode.springboot.model.domian.user.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: xb
 * @Description: AR模式
 * @Date: 2020/9/14 14:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArTest {
    /**
     * AR插入方法
     */
    @Test
    public void testInsert() {
        Users users = new Users();
        users.setAccount("张雄威的账号");
        users.setEmail("923031698@qq.com");
        users.setPassword("123");
        users.setNick("");
        users.setPhone("18667039325");
        users.setName("张雄威");
        users.setAge(40);
        boolean insert = users.insert();
        System.out.println("插入是否成功：" + insert);
    }

    /**
     *
     */
    @Test
    public void selectById() {
        Users users = new Users();
        //  users.setId("1305386071451054082");
        Users usersSelect = users.selectById(users);
        System.out.println("查询是否成功：" + usersSelect);
    }


    /**
     * 修改
     */
    @Test
    public void updateById() {
        Users users = new Users();
        // users.setId("1305386071451054082");
        users.setName("jashdkjashdjadhask");
        boolean b = users.insertOrUpdate();
        System.out.println("修改是否成功：" + b);
    }
}