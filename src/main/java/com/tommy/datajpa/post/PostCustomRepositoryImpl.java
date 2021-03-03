package com.tommy.datajpa.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Spring Data JPA 활용
 * CustomRepository 만들기
 */
@RequiredArgsConstructor
@Repository
@Transactional
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        System.out.println("Custom findMyPost");
        return entityManager
                .createQuery("SELECT p FROM Post AS p", Post.class)
                .getResultList();
    }

    @Override
    public void delete(Object entity) {
        System.out.println("Custom delete");
        entityManager.remove(entity);
    }
}
