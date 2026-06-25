package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dtos.PostDTO;
import com.example.demo.Domain.Common.Entity.Post;
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

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Transactional
    public int syncPosts() {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.fromHttpUrl(apiBase)
                .path("/posts")
                .queryParam("_limit", 10)
                .build()
                .toUri();

        ResponseEntity<PostDTO[]> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                PostDTO[].class
        );
        
        if (responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null) {
            log.error("외부 API 연계 장애 발생. 상태코드: {}", responseEntity.getStatusCode());
            throw new MyBizException("연계 장애 발생");
        }

        PostDTO[] postArray = responseEntity.getBody();


        List<Post> entityList = Arrays.stream(postArray)
                .map(dto -> {
                    Post entity = dto.toEntity();
                    entity.setCreateAt(LocalDateTime.now());
                    return entity;
                })
                .toList();

        postRepository.saveAll(entityList);


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
