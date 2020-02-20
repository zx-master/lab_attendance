package com.zx.lab_attendance.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/1 12:08
 * @Description
 */
public class JsonData implements Serializable {

    private static final long SERIAVERSIONID = 1L;

    private Integer code;
    private Object data;
    private String msg;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JsonData() {
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    //成功传入数据
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    //成功传入数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    //失败，传入描述信息
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    //失败，传入描述信，状态码
    public static JsonData buildError(String msg, Integer code) {
        return new JsonData(code, null, msg);
    }

    //成功，传入数据，及描述信息,状态码
    public static JsonData buildSuccess(Object data, String msg, Integer code) {
        return new JsonData(code, data, msg);
    }

    //成功，传入数据，及描述信息
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(0, data, msg);
    }

    //成功，传入数据及状态码
    public static JsonData buildSuccess(Object data, int code) {
        return new JsonData(code, data, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}


