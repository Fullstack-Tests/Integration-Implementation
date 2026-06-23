package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dtos.PostDTO;
import com.example.demo.Domain.Common.Repository.PostRepository;
import com.example.demo.Exception.MyBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    @Autowired
    private PostRepository postRepository;

    @Value("${link.api.base}")
    private String apiBase;   // 외부 연계 대상 base URL (application.properties)

    // TODO: 외부 API 연계(수신) → 변환 → DB 저장
    //  - @Transactional 적용
    //  - RestTemplate + UriComponentsBuilder 로 (apiBase + "/posts", ?_limit=10) GET 호출
    //    예: restTemplate.exchange(url, HttpMethod.GET, null, PostDTO[].class)
    //  - 상태코드가 HttpStatus.OK 가 아니거나 body 가 null 이면 MyBizException 발생 (연계 장애)
    //  - 응답 PostDTO[] 를 돌며 createAt=now() 설정 후 postRepository.save(dto.toEntity())
    //  - 저장 건수(int) 반환
    @Override
    public int syncPosts() {
        throw new UnsupportedOperationException("TODO: syncPosts 구현");
    }

    // TODO: DB 저장된 연계 데이터 목록 재제공
    //  - @Transactional(readOnly = true)
    //  - findAll() → PostDTO.from 으로 매핑하여 List 반환
    @Override
    public List<PostDTO> getPosts() {
        throw new UnsupportedOperationException("TODO: getPosts 구현");
    }

    // TODO: 단건 재제공
    //  - @Transactional(readOnly = true)
    //  - findById(id) (없으면 MyBizException) → PostDTO.from
    @Override
    public PostDTO getPost(Long id) {
        throw new UnsupportedOperationException("TODO: getPost 구현");
    }
}
