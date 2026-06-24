package com.example.demo.Controller;

import com.example.demo.Domain.Common.Dtos.PostDTO;
import com.example.demo.Domain.Common.Service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/link")
@CrossOrigin(originPatterns = {"http://localhost:[*]"})
public class LinkController {

    @Autowired
    private LinkService linkService;

    // TODO: 수동 동기화(연계 수신) — POST /api/link/sync
    //  - linkService.syncPosts() 호출
    //  - 저장 건수(count) + "연계 동기화 성공!" 메시지를 Map 으로 200 반환
    //  - (보안: SecurityConfig 에서 ADMIN 권한으로 보호)
    @PostMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> sync()
    {
        Map<String, Object> responseMap = new HashMap<>(); // 데이터를 담을 Map 생성
        int count = linkService.syncPosts(); // 동기화 후 저장 건수 반환
        responseMap.put("count",count); // 저장 건수 Map에 추가
        responseMap.put("message","연계 동기화 성공!"); // 200 + Map 반환
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    // TODO: 연계 데이터 목록 재제공 — GET /api/link/posts
    //  - linkService.getPosts() 결과를 200 반환
    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> posts() // 반환 타입 미정
    {
        return ResponseEntity.ok(linkService.getPosts()); // 전체 목록 조회 후 200 반환
    }


    // TODO: 단건 재제공 — GET /api/link/posts/{id}
    //  - linkService.getPost(id) 결과를 200 반환
    @GetMapping(value = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> post(@PathVariable("id") Long id) {
        throw new UnsupportedOperationException("TODO: posts 구현");

    }
}
