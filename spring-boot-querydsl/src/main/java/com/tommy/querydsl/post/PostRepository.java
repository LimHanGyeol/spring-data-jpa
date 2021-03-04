package com.tommy.querydsl.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA
 * JPA 쿼리 메서드 (네임드 쿼리)
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    @Query(value = "SELECT p FROM Post AS p WHERE p.title = ?1")
    List<Post> findByTitle(String title);

}
