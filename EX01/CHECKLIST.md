# EX01 체크리스트 (제출 전 자가 점검)

## 1. 연계 데이터 구성 (NCS 1)
- [ ] PostDTO 의 toEntity()/from() 변환을 구현했습니다
- [ ] 산출물: 연계_요구사항_분석서 / 연계(인터페이스)_정의서 / 연계(인터페이스)_명세서 작성

## 2. 연계 메커니즘 구성 (NCS 2)
- [ ] RestTemplate + UriComponentsBuilder 로 외부 API(GET /posts)를 호출합니다
- [ ] 상태코드 != 200 또는 응답 null 시 MyBizException → @RestControllerAdvice 로 처리합니다
- [ ] LinkScheduler 의 @Scheduled 로 주기 동기화가 동작합니다
- [ ] SecurityConfig: /api/link/sync=ADMIN, /api/link/**=인증, httpBasic 적용

## 3. 내외부 연계 모듈 구현·테스트 (NCS 3)
- [ ] POST /api/link/sync (ADMIN) 로 수신·저장이 동작합니다
- [ ] GET /api/link/posts, /posts/{id} 재제공이 동작합니다
- [ ] 수신 데이터가 DB(tbl_post)에 저장됩니다(@Transactional)
- [ ] 연계 테스트(동기화 정상 / 호출 실패) 통과
- [ ] Git 역할별 커밋·머지 이력

## NCS 능력단위요소 매핑
| 요소 | 이 과제에서 |
|:--|:--|
| 1. 연계 데이터 구성 | 외부 응답→PostDTO 매핑 + 연계 정의서/명세서 |
| 2. 연계 메커니즘 구성 | RestTemplate 호출 + 오류처리 + @Scheduled + Security |
| 3. 내외부 연계 모듈 구현 | 수신·저장 + REST 재제공 + 테스트 |
