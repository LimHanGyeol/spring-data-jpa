package com.tommy.datajpa.post;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Data JPA 활용
 * Domain Event Listener
 * Listener 클래스 자체를 만들고 싶지 않을 수 있다.
 * 그럴 경우 여기서 직접 구현할 수 있다.
 *
 * 이렇게 직접 Bean 으로 등록할 경우 PostListener 클래스를 삭제해도 된다.
 */
@Configuration
public class PostRepositoryTestConfig {

    // @Bean
    // public PostListener postListener() {
    //     return new PostListener();
    // }

    @Bean
    public ApplicationListener<PostPublishedEvent> postListener() {
        return event -> {
            System.out.println("-----------------------");
            System.out.println(event.getPost() + " is published !");
            System.out.println("-----------------------");
        };
    }
}
