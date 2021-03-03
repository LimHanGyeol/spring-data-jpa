package com.tommy.datajpa.post;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

/**
 * Spring Data JPA 활용
 * Domain Event Listener
 * ApplicationListener 를 implements 하고 싶지 않을 경우 Event
 *
 * Listener 클래스 자체를 만들고 싶지 않을 수 있다.
 * 그럴 경우 PostRepositoryTestConfig 클래스로 이동하자.
 *
 * PostRepositoryTestConfig 클래스에서 PostListener Bean 을 직접 주입할 경우
 * 해당 클래스는 삭제할 수 있다.
 */
// implements ApplicationListener<PostPublishedEvent>
// public class PostListener {

//     @EventListener
//     public void onApplicationEvent(PostPublishedEvent event) {
//         System.out.println("-----------------------");
//         System.out.println(event.getPost() + " is published !");
//         System.out.println("-----------------------");
//     }
// }
