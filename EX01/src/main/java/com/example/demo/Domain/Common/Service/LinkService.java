package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dtos.PostDTO;
import java.util.List;

public interface LinkService {
    int syncPosts();              // 외부 API 연계(수신) → 변환 → DB 저장, 저장 건수 반환
    List<PostDTO> getPosts();     // DB 저장된 연계 데이터 목록 재제공
    PostDTO getPost(Long id);     // 단건 재제공
}
