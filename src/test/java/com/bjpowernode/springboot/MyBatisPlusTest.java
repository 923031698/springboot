package com.bjpowernode.springboot;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjpowernode.springboot.mapper.users.UsersMapper;
import com.bjpowernode.springboot.model.domian.user.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xb
 * @Description: mybatis-plus 用法测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    UsersMapper usersMapper;


    /**
     * 根据主键id查询   对象
     */
    @Test
    public void selectById() {
        Users users = usersMapper.selectByUserId("1326052508125417473");
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
        users.setNick("");
        users.setPhone("18667039325");
        users.setName("柏金标");
        users.setAge(40);
        //  users.setSex(SexEnum.WOMEN);
        usersMapper.insert(users);
    }


    /**
     * 修改不会插入空值属性，单会插入空字符串属性
     */
    @Test
    public void update() {
        Users users = new Users();
        // users.setId("1301343945864032258");
        users.setName("柏金标");
        users.setNick(null);
        users.setNick("");
        users.setCreateTime(LocalDateTime.now());
        usersMapper.updateById(users);
    }

    /**
     * 使用updateWrapper条件用法
     */
    @Test
    public void updateWrapper() {
        UpdateWrapper updateWrapper = new UpdateWrapper<Users>();
        updateWrapper.eq("id", "1301343614660886530");
        Users users = new Users();
        users.setName("柏金标");
        users.setCreateTime(LocalDateTime.now());
        int rows = usersMapper.update(users, updateWrapper);
        System.out.println("修改记录数：" + rows);
    }

    /**
     * 使用LambdaUpdateWrapper条件用法
     */
    @Test
    public void lambdaUpdateWrapper1() {
        // LambdaUpdateWrapper lambdaUpdateWrapper = new UpdateWrapper<Users>().lambda();
        // LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper<Users>();
        LambdaUpdateWrapper<Users> lam = Wrappers.lambdaUpdate();
        // new LambdaUpdateWrapper<Users>().eq(Users::getId, "1301343614660886530");
        lam.eq(Users::getId, "1301343614660886530");
        Users users = new Users();
        users.setName("柏金标");
        users.setCreateTime(LocalDateTime.now());
        int rows = usersMapper.update(users, lam);
        System.out.println("修改记录数：" + rows);
    }


    /**
     * 使用LambdaUpdateWrapper2条件用法
     */
    @Test
    public void lambdaUpdateWrapper2() {
        LambdaUpdateWrapper lambdaUpdateWrapper = new UpdateWrapper<Users>().lambda();
        // LambdaUpdateWrapper lambdaUpdateWrapper = new LambdaUpdateWrapper<Users>();
        LambdaUpdateWrapper<Users> lam = Wrappers.lambdaUpdate();
        // new LambdaUpdateWrapper<Users>().eq(Users::getId, "1301343614660886530");
        lam.eq(Users::getId, "1301343614660886530").set(Users::getName, "张雄威");
        // new UpdateWrapper<Users>().lambda().eq(Users::getId, "1301343614660886530").set(Users::getName, "张雄威");
        //  new LambdaUpdateWrapper<Users>().eq(Users::getId, "1301343614660886530").set(Users::getName, "张雄威");
//        Users users = new Users();
//        users.setName("柏金标");
//        users.setCreateTime(LocalDateTime.now());
        int rows = usersMapper.update(null, lam);
        System.out.println("修改记录数：" + rows);
    }

    /**
     * 使用LambdaUpdateWrapper3条件用法
     */
    @Test
    public void lambdaUpdateWrapper3() {
        boolean rows = new LambdaUpdateChainWrapper<Users>(usersMapper)
                .eq(Users::getId, "1301343614660886530")
                .set(Users::getName, "张雄威").update();
        System.out.println("修改记录数：" + rows);
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


    /**
     * 4.名字为王姓 并且（年龄小于40或邮箱不为空）
     * name  like  "王%"    and （age<40 or email is not null）
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        //QueryWrapper<Users> query = Wrappers.<Users>query();
        queryWrapper.likeLeft("name", "王").and(a -> a.le("age", 40).or().isNotNull("email"));
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 5.名字为王姓 或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name  like  "王%"    or （age<40 and age>20  and  email is not null）
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        //QueryWrapper<Users> query = Wrappers.<Users>query();
        queryWrapper.likeLeft("name", "王").or(a -> a.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 6.（年龄小于40或邮箱不为空）并且名字为王姓
     * (age<40 or  email is not null) and like "王%"
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        //QueryWrapper<Users> query = Wrappers.<Users>query();
        queryWrapper.nested(a -> a.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 7.年龄为30,50,20,40
     * age in (30,50,20,40)
     */
    @Test
    public void selectByWrapper7() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList("30", "50", "20", "40"));
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 8.年龄为30,50,20,40
     * age in (30,50,20,40)
     */
    @Test
    public void selectByWrapper8() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList("30", "50", "20", "40"));
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 9.只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    public void selectByWrapper9() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList("30", "50", "20", "40")).last("limit 1");
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 10.只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    public void selectByWrapper10() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList("30", "50", "20", "40")).last("limit 1");
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 查询部分字段
     * 1.名字中包含雨并且年龄小于40岁
     * name  like  "%雨%" and age<40
     */
    @Test
    public void selectByWrapper11() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.select("id", "name", "age").like("name", "雨").lt("age", 40);
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 查询部分字段
     * 1.名字中包含雨并且年龄小于40岁
     * name  like  "%雨%" and age < 40
     */
    @Test
    public void selectByWrapper12() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.select("id", "name", "age").like("name", "雨").lt("age", 40);
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 实体查询
     */
    @Test
    public void selectByWrapper13() {
        Users users = new Users();
        users.setName("雨");
        users.setAge(30);
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>(users);
        //  queryWrapper.select("id", "name", "age").like("name", "雨").lt("age", 40);
        List<Users> list = usersMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    /**
     * AllEq
     */
    @Test
    public void selectByWrapperAllEq() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "柏金标");
        map.put("age", 40);

        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.allEq(map);
        //  queryWrapper.select("id", "name", "age").like("name", "雨").lt("age", 40);
        List<Users> list = usersMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    /**
     * AllEq
     */
    @Test
    public void selectByWrapperMaps() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "柏金标");
        map.put("age", 40);

        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.allEq(map);
        //  queryWrapper.select("id", "name", "age").like("name", "雨").lt("age", 40);
        List<Users> list = usersMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    /**
     * 字段过滤
     */
    @Test
    public void testCondition() {
        BigDecimal.valueOf(1.001);
        condition("白金标", 18);
    }

    public void condition(String name, Integer age) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.select("id", "name", "age")
                .like(StrUtil.isNotBlank(name), "name", name)
                .lt(ObjectUtil.isNotNull(age), "age", age);
        List<Users> users = usersMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 求平均数 分组等等
     */
    @Test
    public void selectByWrapper14() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("name");
        List<Map<String, Object>> maps = usersMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }


    /**
     * 求总记录数
     */
    @Test
    public void selectByWrapperCount() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.like("name", "雨").lt("age", 40);
        Integer count = usersMapper.selectCount(queryWrapper);
        System.out.println("总记录数:" + count);
    }

    /**
     * 返回一个对象
     */
    @Test
    public void selectByWrapperOne() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.like("name", "雨").lt("age", 40);
        Users users = usersMapper.selectOne(queryWrapper);
        System.out.println(users.toString());
    }

    /**
     * lambda表达式用法1
     */
    @Test
    public void selectByWrapperLambda() {
        LambdaQueryWrapper<Users> lambda = new QueryWrapper<Users>().lambda();
        //LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //LambdaQueryWrapper<Users> lam = Wrappers.<Users>lambdaQuery();
        new LambdaQueryWrapper<Users>().like(Users::getName, "雨").lt(Users::getAge, 40);
        List<Users> users = usersMapper.selectList(lambda);
        System.out.println(users.toString());
    }

    /**
     * lambda表达式用法2
     */
    @Test
    public void selectByWrapperLambda3() {
        List<Users> list = new LambdaQueryChainWrapper<Users>(usersMapper).like(Users::getPhone
                , "18667039325").list();
        System.out.println(list.toString());
    }

    /**
     * 自定义xml
     */
    @Test
    public void selectByUserId() {
        Users users = usersMapper.selectByUserId("1305398022210150402");
        System.out.println(users.toString());
    }

    /**
     * lambda分页
     */
    @Test
    public void selectPage() {
        IPage<Users> page = usersMapper.selectPage(new Page<>(1, 2), new LambdaQueryWrapper<Users>().like(Users::getPhone, "18667039325"));
        System.out.println("总页数:" + page.getPages());
        System.out.println("总记录数:" + page.getTotal());
        List<Users> list = page.getRecords();
        list.forEach(System.out::println);
    }

    /**
     * 简单删除
     */
    @Test
    public void deleteById() {
        int rows = usersMapper.deleteById("1301352545244246017");
        System.out.println("删除条数：" + rows);
    }

    /**
     * 简单删除
     */
    @Test
    public void deleteByMap() {
        Map map = new HashMap();
        map.put("name", "张雄威");
        int rows = usersMapper.deleteByMap(map);
        System.out.println("删除条数：" + rows);
    }

}
