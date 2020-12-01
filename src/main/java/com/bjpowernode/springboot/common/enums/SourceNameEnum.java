package com.bjpowernode.springboot.common.enums;

/**
 * @author: xb
 * @create: 2020-12-01 11:31
 */
public enum SourceNameEnum {
    read("read"), write("write");

    private String value;

    SourceNameEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
