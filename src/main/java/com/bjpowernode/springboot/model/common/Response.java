package com.bjpowernode.springboot.model.common;

import lombok.Data;

@Data
public class Response<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 请求成功时返回的对象
     */
    private T data;

    /**
     * 提示信息
     */
    private String msg;


}