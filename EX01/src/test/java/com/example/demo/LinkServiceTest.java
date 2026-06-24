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
        // syncPosts()를 호출해 데이터를 DB에 저장하고, 반환된 저장 건수 count에 저장
        int count = linkService.syncPosts();
        // 반환된 저장 건수 count가 0보다 큰지 검증
        assertTrue(count > 0, "저장 건수는 0 보다 커야 합니다."); // syncPosts(): DB 저장, 저장 건수 반환
    }
}
