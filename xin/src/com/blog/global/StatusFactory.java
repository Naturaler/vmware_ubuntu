package com.blog.global;

/**
 * Created by xin on 17-5-14.
 */
public class StatusFactory {
    public final static int STATUS_SUCCESS = 200;
    public final static int STATUS_BADREQUEST = 400;
    public final static int STATUS_SERVER_ERROR = 500;
    /**
     * 根据状态吗设置返回状态
     * @param code 状态码：200,400,500
     * @return
     */
    public static Status getStatusByCode(int code) {
        switch (code) {
            case STATUS_SUCCESS:
                return new Status(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS_CODE);
            case STATUS_BADREQUEST:
                return new Status(Constants.ILLEGAL_ARGUMENT, Constants.ILLEGAL_ARGUMENT_CODE);
            case STATUS_SERVER_ERROR:
                return new Status(Constants.SERVER_ERROR, Constants.SERVER_ERROR_CODE);
            default:
                throw new RuntimeException(" server error exception !");
        }
    }
}
