package com.bjpowernode.springboot.handler.exception;

import com.bjpowernode.springboot.common.enums.ErrorTypeEnum;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {
    private Integer code;

    /**
     * 使用已有的错误类型
     *
     * @param type 枚举类中的错误类型
     */
    public CustomException(ErrorTypeEnum type) {
        super(type.getMsg());
        this.code = type.getCode();
    }

    /**
     * 自定义错误类型
     *
     * @param code 自定义的错误码
     * @param msg  自定义的错误提示
     */
    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
