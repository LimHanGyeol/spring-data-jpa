package com.tommy.datajpa;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Spring Data JPA
 * 엔티티 상태와 Cascade
 *
 */
@Component
public class PostRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post("Spring Data JPA 진행중..");
//
        Comment comment1 = new Comment("남은 강의는 3개");
        post.addComment(comment1);
//
        Comment comment2 = new Comment("JPA 는 목요일 까지");
        post.addComment(comment2);

        Session session = entityManager.unwrap(Session.class);
        // Cascade - Removed 를 이용하면 Post 에 의존하고 있는 Comment 들이 전부 삭제된다.
        // Post post = session.get(Post.class, 1L);
        // session.delete(post);
        session.save(post);
    }
}
