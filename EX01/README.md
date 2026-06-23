# EX01 — 통합 구현 (외부 OpenAPI 연계 : S-Link)

> 분류: 모듈시험(조별) · 예상 3시간 · 합격선 60점
> NCS 능력단위: 통합 구현(2001020206) — 능력단위요소 1~3
> 기술 스택: Spring Boot 3.x · RestTemplate · JPA · @Scheduled · Spring Security · @RestControllerAdvice · MySQL testdb · 포트 8090

## 과제 개요
외부 시스템(공개 OpenAPI)을 연계하여 데이터를 수신하고, 이를 표준 형식(DTO)으로 변환하여 데이터베이스에 저장합니다. 저장한 데이터는 본 시스템의 REST API 로 다시 제공하며, 연계는 `@Scheduled` 배치로 주기적으로 동기화합니다. 또한 연계 과정에서 발생하는 오류를 처리하고, 연계 요청에 대한 접근을 Spring Security 로 보호합니다.

- 외부(송신) 시스템 : `https://jsonplaceholder.typicode.com` (인증 불필요, 기본)
  ※ 공공데이터포털 OpenAPI(예: 기상청 단기예보 등)로 교체할 수 있습니다. base URL·serviceKey 는 `application(-dev).properties` 로 설정하며(인증키는 보안상 분리합니다), PostDTO 필드와 연계 정의서·명세서는 해당 응답에 맞게 수정합니다.
- 연계 데이터 : 게시글 Post `{ userId, id, title, body }`
- 연계 흐름 : 외부 API(수신) → 변환(PostDTO) → DB 저장(Post) → REST API 재제공 / `@Scheduled` 주기 동기화

## 사용 기술 범위
EAI/ESB 솔루션, 메시지 큐, Spring Batch 등 외부 통합 솔루션은 사용하지 않고, Spring 기본 기능만 사용합니다. 외부 연계는 `RestTemplate`·`UriComponentsBuilder` 로, 배치는 Spring 기본 `@Scheduled` 로 처리합니다.

## 기능 일람
| 기능 | 메서드/경로 | 처리 |
|:--|:--|:--|
| 수동 동기화(연계 수신) | POST `/api/link/sync` | 외부 API 호출 → 변환 → DB 저장 (ADMIN 권한) |
| 연계 데이터 재제공 | GET `/api/link/posts` | DB 에 저장된 연계 데이터 목록 (인증 필요) |
| 단건 재제공 | GET `/api/link/posts/{id}` | 단건 조회 |
| 배치 동기화 | `@Scheduled` | 주기적으로 외부 API 를 자동 연계 |

## 폴더 구조
```
EX01/
├─ README.md / CHECKLIST.md
├─ build.gradle / settings.gradle            (제공)
├─ db/schema.sql                             (제공)
├─ docs/ (작업지시서 / 설계과제 / 채점기준_체크리스트 / 가이드)
│  └─ 산출물_양식/ (연계_요구사항_분석서 / 연계(인터페이스)_정의서 / 연계(인터페이스)_명세서)
└─ src/main/java/com/example/demo/
   ├─ DemoApplication.java                   (제공: @EnableScheduling)
   ├─ Config/SecurityConfig.java             ← 구현 (FilterChain)
   ├─ Controller/LinkController.java         ← 구현 (sync/posts)
   ├─ Controller/GlobalException/GlobalExceptionHandler.java (제공)
   ├─ Domain/Common/
   │  ├─ Entity/Post.java                    (제공)
   │  ├─ Repository/PostRepository.java      ← 구현 (선언)
   │  ├─ Service/LinkService.java            (제공: 인터페이스)
   │  ├─ Service/LinkServiceImpl.java        ← 구현 (syncPosts/getPosts/getPost)
   │  └─ Dtos/PostDTO.java                   (제공: 속성·검증) ← 구현 (toEntity/from)
   ├─ Scheduled/LinkScheduler.java           ← 구현 (@Scheduled)
   └─ Exception/MyBizException.java          (제공)
```
> DataSource·EntityManagerFactory·TransactionManager 는 Spring Boot 자동설정을 사용합니다. `@Scheduled` 활성화를 위한 `@EnableScheduling` 은 DemoApplication 에 적용되어 있습니다.

## 실행 방법
1. MySQL `testdb` 에 `db/schema.sql` 을 실행합니다(테이블 생성).
2. `gradlew bootRun` 으로 애플리케이션을 기동합니다(포트 8090).
3. 인증 후 `POST /api/link/sync` 로 외부 데이터를 동기화하고, `GET /api/link/posts` 로 결과를 확인합니다.
4. 콘솔에서 `@Scheduled` 배치 동기화 로그를 확인합니다.

## 배점 (100점 / 합격선 60)
| 파트 | 배점 |
|:--|--:|
| 1. 연계 데이터 구성 | 30 |
| 2. 연계 메커니즘 구성 | 35 |
| 3. 내외부 연계 모듈 구현·테스트 | 35 |

상세 설명은 `docs/설계과제.md`, 채점 항목은 `docs/채점기준_체크리스트.md`, 구현 시 참고 사항은 `docs/가이드.md` 를 확인합니다. 산출물 문서는 `docs/산출물_양식/` 의 3종을 작성하여 제출합니다.
