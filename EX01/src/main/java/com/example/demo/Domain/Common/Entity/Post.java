package com.example.demo.Domain.Common.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "tbl_post")
public class Post {
    // 외부 시스템 식별자를 그대로 PK 로 사용(자동증가 아님) → 재동기화 시 upsert
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(length = 500)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
