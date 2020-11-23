package com.leon.api.result;


public enum ResultCode {

    SUCCESS(200,"成功!"),
    USER_NOT_EXIST(401,"用户不存在!");

    private Integer code;

    private String message;

    ResultCode(Integer code,  String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

}
