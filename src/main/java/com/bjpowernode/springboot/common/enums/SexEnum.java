package com.bjpowernode.springboot.common.enums;


import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;


/**
 * @Author bjb
 * @Description 性别枚举
 * @Date 2020/5/20 20:48
 */
@Getter
public enum SexEnum implements IEnum<Integer> {
    /**
     * 男
     */
    MAN(1, "男"),

    /**
     * 女
     */
    WOMEN(2, "女");
    private int code;
    private String description;

    SexEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.description;
    }
}