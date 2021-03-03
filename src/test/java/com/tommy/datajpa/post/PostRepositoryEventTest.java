package com.tommy.datajpa.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Spring Data JPA 활용
 * Domain Event
 * PostRepository 에 너무 많은 테스트코드가 생겨 각 챕터의 학습한 내용을 알아보기 힘들어 두번째 테스트 클래스를 만들었다.
 */
@DataJpaTest
@Import(PostRepositoryTestConfig.class)
public class PostRepositoryEventTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void event() {
        Post post = new Post("event");
        PostPublishedEvent event = new PostPublishedEvent(post);

        applicationContext.publishEvent(event);
    }

    @Test
    void create() {
        Post post = new Post("hibernate");

        postRepository.save(post.publish());

        assertThat(postRepository.existsByTitle("hibernate")).isTrue();
    }
}
