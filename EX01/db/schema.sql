-- S-Link 연계 데이터 스키마 (MySQL testdb / localhost:3306 / root / 1234)
-- 외부 게시글(Post)을 수신·저장. id 는 외부 시스템의 식별자를 그대로 사용(upsert).
DROP TABLE IF EXISTS tbl_post;

CREATE TABLE tbl_post (
    id        BIGINT       NOT NULL,          -- 외부 시스템 식별자(PK, 자동증가 아님)
    user_id   BIGINT,
    title     VARCHAR(500),
    body      TEXT,
    create_at TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
