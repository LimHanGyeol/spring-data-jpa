package com.tommy.querydsl.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void findByTitleStartWith() {
        // given
        savePost();

        // when
        List<Post> posts = postRepository.findByTitleStartsWith("Spring");

        // then
        assertThat(posts).hasSize(1);
    }

    @Test
    void findByTitle() {
        // given
        savePost();

        // when
        List<Post> posts = postRepository.findByTitle("Spring Data JPA");

        // then
        assertThat(posts).hasSize(1);
    }

    private void savePost() {
        Post post = new Post("Spring Data JPA");
        postRepository.save(post);
    }
}
