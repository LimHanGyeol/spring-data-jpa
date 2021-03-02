package com.tommy.datajpa.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Spring Data Common
 * 인터페이스 정의하기
 */
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void create() {
        // given
        Comment comment = new Comment("Hello Comment");
        assertThat(comment.getId()).isNull();

        // when
        Comment savedComment = commentRepository.save(comment);

        // then
        assertThat(savedComment).isNotNull();
    }

    @Test
    void findAll() {
        // given
        Comment comment = new Comment("Find Comment");
        assertThat(comment.getId()).isNull();
        Comment savedComment = commentRepository.save(comment);

        // when
        List<Comment> comments = commentRepository.findAll();
        long count = commentRepository.count();

        // then
        assertThat(comments).hasSize(1);
        assertThat(comments).contains(savedComment);
        assertThat(count).isEqualTo(1);
    }
}
