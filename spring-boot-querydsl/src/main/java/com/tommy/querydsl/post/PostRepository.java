package com.tommy.querydsl.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA
 * JPA 쿼리 메서드 (네임드 쿼리), Named Parameter
 * Sort Type 을 매개변수로 줄 경우 반드시 Property, Alias 만을 정렬 기준으로 사용해야 한다.
 * 그렇지 않을 경우(함수 등 ..) 예외가 발생한다.
 * Ex) must only property references or aliases used in the select clause.
 * 이를 우회하려면 Sort Type 에 JpaSort.unsafe() 함수를 사용하면, 정렬 기준을 함수로도 줄 수 있다.
 * Ex) JpaSort.unsafe("LENGTH(title)")
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    // p.title AS pTitle 의 식으로 Alias 도 사용할 수 있다.
    // Named Parameter -> :title
    // SpEL -> #{#entityName} 이렇게 사용하면 Post 로 선언한 타입이 안에 들어온다.
    @Query(value = "SELECT p FROM #{#entityName} AS p WHERE p.title = :title")
    List<Post> findByTitle(@Param("title") String keyword, Sort sort);

}
