package com.bjpowernode.springboot.common.enums;

/**
 * 自定义异常枚举
 */
public enum ErrorTypeEnum {

    /**
     * 错误类型
     */
    OBJECT_NOT_FOUND(0, "对象不存在"),

    INVALID_PARAMS(1, "参数不正确"),

    RESULT_NOT_EXIST(2, "记录不存在");

    /**
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    ErrorTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
