package com.example.demo;

import com.example.demo.Domain.Common.Service.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LinkServiceTest {

    @Autowired
    private LinkService linkService;

    // TODO: 연계 동기화 정상 테스트 (NCS 3 — 연계 테스트)
    //  - linkService.syncPosts() 호출 (외부망 필요)
    //  - 반환된 저장 건수가 0 보다 큰지 assertTrue
    //  - 이어서 linkService.getPosts() 가 비어있지 않은지 확인
    @Test
    void syncPosts_정상() {
        fail("TODO: syncPosts 정상 동기화 테스트 구현");
    }
}
