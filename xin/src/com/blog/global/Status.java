package com.blog.global;

/**
 * Created by xin on 17-5-14.
 */
public class Status {
    private String msg;
    private Integer code;

    public Status(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Status{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
