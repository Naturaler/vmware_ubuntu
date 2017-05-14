package com.blog.dto;

import com.blog.global.Status;

/**
 * Created by xin on 17-5-14.
 */
public class ResponseDto {
    private Status status;
    private Dto data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Dto getData() {
        return data;
    }

    public void setData(Dto data) {
        this.data = data;
    }
}
