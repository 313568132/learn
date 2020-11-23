package com.leon.api.result;

import lombok.Data;

@Data
public class Result {

    public Integer code;

    public String message;

    public Object data;

    public Result(ResultCode resultCode){
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public Result(ResultCode resultCode, Object data){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public static Result success(){
        ResultCode resultCode = ResultCode.SUCCESS;
        return new Result(resultCode);
    }

    public static Result success(Object data){
        ResultCode resultCode = ResultCode.SUCCESS;
        return new Result(resultCode,data);
    }

    public static Result fail(ResultCode resultCode){
        return new Result(resultCode);
    }

    public static Result fail(ResultCode resultCode,Object data){
        return new Result(resultCode,data);
    }
}
