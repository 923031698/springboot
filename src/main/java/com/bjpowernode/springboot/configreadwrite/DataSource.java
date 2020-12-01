package com.bjpowernode.springboot.configreadwrite;

import com.bjpowernode.springboot.common.enums.SourceNameEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 数据源key值
     *
     * @return
     */
    SourceNameEnum value();

}
