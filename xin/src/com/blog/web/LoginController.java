package com.blog.web;

import com.blog.dto.ResponseDto;
import com.blog.entity.User;
import com.blog.global.StatusFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xin on 17-5-22.
 */
@RestController
public class LoginController {
    @RequestMapping("/login")
    public ResponseDto login(User user) {
        ResponseDto responseDto = new ResponseDto();
        if (user.getUsername().equals("admin") && user.getPassword().equals("123")) {
            responseDto.setStatus(StatusFactory.getStatusByCode(StatusFactory.STATUS_SUCCESS));
        }else {
            responseDto.setStatus(StatusFactory.getStatusByCode(StatusFactory.STATUS_BADREQUEST));
        }
        return responseDto;
    }
}
