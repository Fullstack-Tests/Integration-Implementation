package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "연계(통합 구현) 처리 중 오류")
public class MyBizException extends RuntimeException {
    public MyBizException(String message) {
        super(message);
    }
}
