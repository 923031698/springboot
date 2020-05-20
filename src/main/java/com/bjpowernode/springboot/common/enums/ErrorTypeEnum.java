package com.bjpowernode.springboot.common.enums;

/**
 * @Author bjb
 * @Description 自定义枚举异常
 * @Date 2020/5/20 20:48
 */
public enum ErrorTypeEnum {

    /**
     * 统一成功返回
     */
    OPERATION_SUCCESS(2000, "操作成功"),

    OPERATION_FAILURE(3000, "操作失败"),

    //CRUD操作失败
    ADD_FAILURE(3001, "添加失败"),
    QUERY_FAILURE(3002, "查询失败"),
    DELETE_FAILURE(3003, "删除失败"),
    UPDATE_FAILURE(3004, "修改失败");
    ;

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
