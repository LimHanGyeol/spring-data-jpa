package com.tommy.datajpa.account;

import com.tommy.datajpa.Post;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Spring Data JPA
 * Query
 * JPA 와 Hibernate 에서 Query 를 사용하는 방법
 */
@Component
public class JpaQueryRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // JPQL (HQL)
         TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post AS  p", Post.class);
         List<Post> posts = query.getResultList();

         printPosts(posts);

        // Criteria - TypeSafe Query
        // CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        // Root<Post> root = criteriaQuery.from(Post.class);
        // criteriaQuery.select(root);

        // List<Post> posts = entityManager.createQuery(criteriaQuery).getResultList();
        // printPosts(posts);

        // Native Query
        // List<Post> posts = entityManager.createNativeQuery("SELECT * FROM Post", Post.class).getResultList();
        // printPosts(posts);
    }

    private void printPosts(List<Post> posts) {
        posts.forEach(post -> {
            System.out.println("<-------------->");
            System.out.println(post.toString());
        });
    }
}
