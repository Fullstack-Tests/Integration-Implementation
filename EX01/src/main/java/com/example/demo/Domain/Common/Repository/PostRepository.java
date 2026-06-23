package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TODO: JpaRepository<Post, Long> 를 상속하도록 선언하라.
//  (기본 save/findAll/findById/deleteById 등을 자동 제공받는다)
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
