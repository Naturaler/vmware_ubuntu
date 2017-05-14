package com.blog.global;

/**
 * Created by xin on 17-5-14.
 */
public class StatusFactory {
    public static Status getStatusByCode(int code) {
        switch (code) {
            case 200:
                return new Status(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS_CODE);
            case 400:
                return new Status(Constants.ILLEGAL_ARGUMENT, Constants.ILLEGAL_ARGUMENT_CODE);
            case 500:
                return new Status(Constants.SERVER_ERROR, Constants.SERVER_ERROR_CODE);
            default:
                throw new RuntimeException(" server error exception !");
        }
    }
}
