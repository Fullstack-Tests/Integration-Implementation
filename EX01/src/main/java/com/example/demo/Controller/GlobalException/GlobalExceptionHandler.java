package com.example.demo.Controller.GlobalException;

import com.example.demo.Exception.MyBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 연계 비즈니스 예외 → 502 (민감정보 미노출)
    @ExceptionHandler(MyBizException.class)
    public ResponseEntity<Map<String, Object>> handleBiz(MyBizException e) {
        log.error("[Link/Biz] " + e.getMessage());
        Map<String, Object> body = new HashMap<>();
        body.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(body);
    }

    // 그 외 예외 → 500 (스택트레이스/내부정보 미노출)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception e) {
        log.error("[Link/Etc] " + e);
        Map<String, Object> body = new HashMap<>();
        body.put("error", "연계 처리 중 오류가 발생했습니다.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
