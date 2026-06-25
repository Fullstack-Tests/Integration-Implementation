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

    // 지정 주기마다 자동 실행 설정.
    // @Scheduled: 스프링에서 정해진 주기마다 메서드를 자동으로 실행하게 하는 어노테이션
    // fixedDelayString: 이전 작업이 끝난 시점부터 일정한 지연 시간(밀리초 단위)을 두고 다음 작업을 실행하도록 지정
    @Scheduled(fixedDelayString = "${link.sync.delay:60000}")
    public void scheduledSync() {
        // TODO: @Scheduled + syncPosts 호출 구현
        try{
            // syncPosts(): DB 저장, 저장 건수 반환
            // syncPosts()를 호출해 연계 동기화를 수행하고, 저장된 총 건수(n)을 반환받는다
            int n = linkService.syncPosts();
            log.info("[Link] 배치 동기화 완료 - " + n + "건");
        }catch(Exception e) {
            // try-catch로 예외 발생 시 멈추지 않도록 감싸고 에러 로그를 남긴다
            log.error("[Link] 배치 동기화 실패: " + e.getMessage());
        }

    }
}
