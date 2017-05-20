package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by xin on 17-5-20.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends BaseException {
    public IllegalArgumentException(String message) {
        super(message);
    }
}
