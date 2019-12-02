package com.where.soul.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lw
 */
public class Result {
    private Integer code;
    private String message;
    private Object data;
    private Result() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setCode(ResultEnum.SUCCESS.getResultCode());
        result.setMessage(ResultEnum.SUCCESS.getResultMessage());
        result.setData(data);
        return result;
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setData(null);
        result.setCode(resultEnum.getResultCode());
        result.setMessage(resultEnum.getResultMessage());
        return result;
    }

    public static Result error(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setCode(ResultEnum.ERROR.getResultCode());
        result.setMessage(ResultEnum.ERROR.getResultMessage());
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getResultCode());
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
