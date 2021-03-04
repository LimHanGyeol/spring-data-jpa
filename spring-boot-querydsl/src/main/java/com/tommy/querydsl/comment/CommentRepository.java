package com.tommy.querydsl.comment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA
 * JPA. EntityGraph
 * EntityGraph -> NamedEntityGraph 에 정의되어 있는 엔티티 그룹을 사용한다.
 * 그래프 타입 설정 기능
 * FETCH (DEFAULT) : 설정한 엔티티 애트리뷰트는 EAGER 패치. 나머지는 LAZY 패치
 * LOAD : 설정한 엔티티 애트리뷰트는 EAGER 패치 나머지는 기본 패치 전략을 따른다.
 *
 * Entity 에 NamedEntityGraph 를 만들지 않고
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * attributePath = {"post"} 로 정의할 수도 있다. 이게 더 깔끔할 것이다.
     */
    @EntityGraph(value = "Comment.post")
    Optional<Comment> loadById(Long id);
}
