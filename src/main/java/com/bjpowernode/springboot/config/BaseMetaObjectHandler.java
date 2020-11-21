package com.bjpowernode.springboot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;


/**
 * @Author: xb
 * @Description: mybatis-plus 元对象字段填充控制器
 * @Date: 2020/11/10 17:51
 */
@Slf4j
public class BaseMetaObjectHandler implements MetaObjectHandler {

    /**
     * 该类用来做crud操作时的补充操作、可自行模仿下面案例
     */

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("默认插入时自动填充字段");
        if (metaObject.hasGetter("createTime")) {
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("默认插修改时自动填充字段");
        if (metaObject.hasGetter("updateTime")) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}