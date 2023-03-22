package com.aili.wechatdome.utils.result;

/**
 * @描述 返回结果枚举
 */
public enum Status {
    OK(200,"请求成功"),
    SYSTEM_ERROR(501,"系统异常"),
    SQL_ERROR(502,"SQL语句异常"),
    REQ_ERROR(503,"请求错误"),
    USER_ERROR(504,"用户名错误"),
    PSW_ERROR(505,"用户名错误");
    ;
    //状态码
    private Integer code;
    //错误信息
    private String message;
    //构造器
    Status(Integer code,String message){
        this.code=code;
        this.message=message;
    }
    //只需要添加get方法就行
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code+":"+message;
    }
}
