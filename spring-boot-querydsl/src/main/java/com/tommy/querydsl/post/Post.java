package com.tommy.querydsl.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Spring Data JPA
 * JPA 쿼리 메서드 (네임드 쿼리)
 * 하지만 Domain 객체에 이렇게 작성하는 것보단 Repository 에 Query 어노테이션을 주고 작성하는게 더 낫다.
 */
// @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post AS p WHERE p.title = ?1")
@NoArgsConstructor
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @CreatedDate
    private LocalDate createdAt;

    public Post(String title) {
        this.title = title;
        this.createdAt = LocalDate.now();
    }
}
