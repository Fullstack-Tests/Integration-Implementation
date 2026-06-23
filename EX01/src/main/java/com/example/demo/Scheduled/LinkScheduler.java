package com.example.demo.Scheduled;

import com.example.demo.Domain.Common.Service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * [NCS 2. 연계 메커니즘 — 배치 주기 동기화]
 *  - @EnableScheduling 은 DemoApplication 에 이미 적용되어 있다.
 *  - @Scheduled : 정해진 주기마다 메서드를 자동 실행한다.
 *      · fixedDelayString = "${link.sync.delay:60000}" → 설정값(없으면 60초)마다 실행
 *  - 배치는 예외로 스케줄러가 멈추지 않도록 try-catch 로 감싼다.
 */
@Component
@Slf4j
public class LinkScheduler {

    @Autowired
    private LinkService linkService;

    // TODO: ★학생 작업★ 주기적 연계 동기화
    //  1) 이 메서드 위에 @Scheduled(fixedDelayString = "${link.sync.delay:60000}") 를 붙인다.
    //  2) try { int n = linkService.syncPosts(); log.info("[Link] 배치 동기화 완료 - " + n + "건"); }
    //     catch (Exception e) { log.error("[Link] 배치 동기화 실패: " + e.getMessage()); }
    public void scheduledSync() {
        // TODO: @Scheduled + syncPosts 호출 구현
    }
}
