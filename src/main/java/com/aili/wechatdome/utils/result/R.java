package com.aili.wechatdome.utils.result;

import org.springframework.stereotype.Component;

/**
 * @描述
 */
public class R<T> {
    //状态码
    private Integer status;
    //错误的状态信息
    private String message;
    //数据
    private T data;

    //get方法：获取状态码
    public Integer getStatus() {
        return status;
    }
    //get方法：获取错误状态信息
    public String getMessage() {
        return message;
    }
    //get方法：获取数据内容
    public T getData() {
        return data;
    }
    /**
     * 构造器(私有化)，这里写了3个构造器。根据实际发开需要添加即可
     */
    private R(Integer status,String message,T data){
        this.status=status;
        this.message=message;
        this.data=data;
    }
    private R(Integer status,String message){
        this.status=status;
        this.message=message;
    }
    private R(String message){
        this.message=message;
    }

    //下面就是根据需要返回不同参数格式的方法
    /**
     * 返回《状态码》《状态信息》《数据》
     * 状态码来自--->>枚举
     * 状态信息来自--->>开发人员
     * 数据来自--->>开发人员
     */
    public static <T> R<T> buildR(Status status,String message,T data){
        return new R<T>(status.getCode(),message,data);

    }

    /**
     * 返回《状态码》《状态信息》《状态信息》
     * 状态码来自--->>枚举
     * 状态信息来自--->>开发人员
     */
    public static <T> R<T> buildR(Status status,String message){
        return new R<T>(status.getCode(),message);
    }
    /**
     * 返回《状态码》《状态信息》《数据》
     * 状态码来自--->>枚举
     * 状态信息来自--->>枚举
     * 数据来自--->>开发人员
     */
    public static <T> R<T> buildR(Status status,T data){
        return new R<T>(status.getCode(),status.getMessage(),data);
    }
    /**
     * 返回《状态码》《状态信息》《数据》
     * 状态码来自--->>枚举
     * 状态信息来自--->>枚举
     * 数据来自--->>开发人员
     */
    public static <T> R<T> buildR(Status status){
        return new R<T>(status.getCode(),status.getMessage());
    }
}

