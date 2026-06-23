package com.example.demo.Domain.Common.Dtos;

import com.example.demo.Domain.Common.Entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.time.LocalDateTime;

// 외부 응답(JSON)의 여분 필드는 무시 (연계 데이터 표준화)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PostDTO {

    // ===== 속성(외부 응답 ↔ 표준) : 제공 =====
    private Long id;        // 외부 식별자
    private Long userId;    // 외부 userId
    private String title;
    private String body;
    private LocalDateTime createAt;  // 수신 시각(외부에 없음)

    // ===== 기능(변환 메서드) : 학생 구현 =====

    // TODO: DTO → Entity 변환. Post.builder() 로 id/userId/title/body/createAt 매핑하여 반환
    public Post toEntity() {
        throw new UnsupportedOperationException("TODO: toEntity 구현");
    }

    // TODO: Entity → DTO 변환(static). PostDTO.builder() 로 매핑하여 반환
    public static PostDTO from(Post p) {
        throw new UnsupportedOperationException("TODO: from 구현");
    }
}
