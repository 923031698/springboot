package com.bjpowernode.springboot.common.utils;


import cn.hutool.core.util.IdUtil;
import tk.mybatis.mapper.genid.GenId;


/**
 * 分布式id生成策略
 */
public class DistributedGenId implements GenId<String> {

    @Override
    public String genId(String table, String s1) {
        return (IdUtil.getSnowflake(5l, 5l).nextIdStr());
    }






}