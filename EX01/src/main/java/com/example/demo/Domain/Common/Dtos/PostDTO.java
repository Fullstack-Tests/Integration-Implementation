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

    // DTO -> Entity 변환
    // 외부 API에서 받은 DTO 데이터를 DB 저장용 Entity로 변환
    public Post toEntity() {
        // PostDTO(DTO)의 값을 Post(Entity) 필드에 매핑해 변환
        Post post = Post.builder()  // builder(): 객체를 단계적으로 생성하는 패턴
                .id(id)
                .userId(userId)
                .title(title)
                .body(body)
                .createAt(createAt)
                .build();

        return post;
    }

    // TODO: Entity → DTO 변환(static). PostDTO.builder() 로 매핑하여 반환

    // Entity -> DTO 변환
    // DB에 저장된 Entity를 외부 API 응답용 DTO로 변환
    public static PostDTO from(Post p) { // static: 객체 생성 없이 바로 호출하기 위해 사용
        // Post(Entity)의 값을 PostDTO(DTO) 필드에 매핑해 변환
        PostDTO postDTO = PostDTO.builder()
                .id(p.getId())
                .userId(p.getUserId())
                .title(p.getTitle())
                .body(p.getBody())
                .createAt(p.getCreateAt())
                .build();

        return postDTO;
    }
}
