package com.tommy.querydsl.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA
 * JPA 쿼리 메서드 (네임드 쿼리), Named Parameter, Update
 * Sort Type 을 매개변수로 줄 경우 반드시 Property, Alias 만을 정렬 기준으로 사용해야 한다.
 * 그렇지 않을 경우(함수 등 ..) 예외가 발생한다.
 * Ex) must only property references or aliases used in the select clause.
 * 이를 우회하려면 Sort Type 에 JpaSort.unsafe() 함수를 사용하면, 정렬 기준을 함수로도 줄 수 있다.
 * Ex) JpaSort.unsafe("LENGTH(title)")
 *
 * 보통 save 의 Persistent 로 EntityManager 가 상태를 감지하여 엔티티의 변경이 생기고 DB 에 싱크를 할 필요가 있을 경우
 * 알아서 update 쿼리를 친다.
 * 하지만 우리가 직접적으로 UPDATE 쿼리를 정의할 필요가 있을 경우 아래와 같이 사용한다.
 * 그러나 이 방법은 권장하지 않는다. 테스트를 해보면 수정이 되지 않는다.
 * 이유는 select 를 하지 않기 때문이다.
 * 아직 Persistent 하고 있는 객체가 1차 캐시에 남아있기 때문에 Title 이 바뀌지 않는다.
 * Spring 도 이 문제를 알고 있어서 clearAutomatically 옵션을 줄 수 있도록 해놨다.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    // p.title AS pTitle 의 식으로 Alias 도 사용할 수 있다.
    // Named Parameter -> :title
    // SpEL -> #{#entityName} 이렇게 사용하면 Post 로 선언한 타입이 안에 들어온다.
    @Query(value = "SELECT p FROM #{#entityName} AS p WHERE p.title = :title")
    List<Post> findByTitle(@Param("title") String keyword, Sort sort);

    // 권장하지 않음. 객체를 update 해주는 것이 더 나음
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Post AS p SET p.title = ?1 WHERE p.id = ?2")
    int updateTitle(String title, Long id);
}
