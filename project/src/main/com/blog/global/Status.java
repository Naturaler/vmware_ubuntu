package com.blog.global;

/**
 * Created by xin on 17-5-14.
 */
public enum Status {
    IllegalArgument(Constants.ILLEGAL_ARGUMENT, 400),
    OperateSuccess(Constants.OPERATION_SUCCESS, 200),
    ServerError(Constants.SERVER_ERROR, 500)
    ;
    private String msg;
    private Integer code;

    Status(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
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
