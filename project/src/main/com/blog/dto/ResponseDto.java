package com.blog.dto;

import com.blog.global.Status;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

/**
 * Created by xin on 17-5-14.
 */
public class ResponseDto {
    @JsonUnwrapped
    private Status status;
    private List<? extends Dto> data;

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<? extends Dto> getData() {
        return data;
    }

    public void setData(List<? extends Dto> data) {
        this.data = data;
    }
}
