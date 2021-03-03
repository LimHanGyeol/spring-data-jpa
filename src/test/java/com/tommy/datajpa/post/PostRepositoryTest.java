package com.tommy.datajpa.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void create() {
        // given
        Post post = new Post("hello spring boot common");
        assertThat(post.getId()).isNull();

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertThat(savedPost.getId()).isNotNull();
    }

    @Test
    void findAll() {
        // given
        Post post = new Post("hello spring boot common");
        assertThat(post.getId()).isNull();
        Post savedPost = postRepository.save(post);

        // when
        List<Post> posts = postRepository.findAll();

        // then
        assertThat(posts).hasSize(1);
        assertThat(posts).contains(savedPost);
    }

    @Test
    void findAllByPageable() {
        // given
        Post post = new Post("hello spring boot common");
        assertThat(post.getId()).isNull();
        postRepository.save(post);

        // when
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));

        // then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 번호
        assertThat(page.getSize()).isEqualTo(10); // 요청한 페이지 엘리먼트 개수
        assertThat(page.getNumberOfElements()).isEqualTo(1); // 현재 페이지에 있는 엘리먼트 개수
    }

    @Test
    void findByTitleContains() {
        // given
        Post post = new Post("hello spring boot common");
        assertThat(post.getId()).isNull();
        postRepository.save(post);

        Page<Post> page = postRepository.findByTitleContains("spring", PageRequest.of(0, 10));

        // then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 번호
        assertThat(page.getSize()).isEqualTo(10); // 요청한 페이지 엘리먼트 개수
        assertThat(page.getNumberOfElements()).isEqualTo(1); // 현재 페이지에 있는 엘리먼트 개수
    }

    @Test
    void countByTitle() {
        // given
        Post post = new Post("hello spring boot common");
        assertThat(post.getId()).isNull();
        postRepository.save(post);

        // when
        long result = postRepository.countByTitleContains("spring");

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void customMyPost() {
        postRepository.findMyPost();
    }

    @Test
    void delete() {
        Post post = new Post("spring data jpa");
        postRepository.save(post);

        postRepository.delete(post);
        postRepository.flush();
    }
}
