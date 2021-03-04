package com.tommy.querydsl.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

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
        List<Post> posts = postRepository.findByTitle("Spring Data JPA", Sort.by("title"));

        // then
        assertThat(posts).hasSize(1);
    }

    @Test
    void updateTitle() {
        // given
        Post savedPost = savePost();

        // when
        String hibernate = "hibernate";
        int update = postRepository.updateTitle(hibernate, savedPost.getId());

        // then
        assertThat(update).isEqualTo(1);

        // when
        Optional<Post> byId = postRepository.findById(savedPost.getId());

        // then
        Post post = byId.get();
        assertThat(post.getTitle()).isEqualTo(hibernate);
    }

    @Test
    void updateTitleByPost() {
        // given
        Post savedPost = savePost();

        // then
        savedPost.updateTitle("hibernate");

        // then
        List<Post> posts = postRepository.findAll();
        assertThat(posts.get(0).getTitle()).isEqualTo("hibernate");
    }

    private Post savePost() {
        Post post = new Post("Spring Data JPA");
        return postRepository.save(post);
    }
}
