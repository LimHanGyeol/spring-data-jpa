package com.tommy.datajpa.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Spring Data Common
 * 인터페이스 정의하기, Null 처리
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

    @Test
    void findById() {
        // given
        Comment comment = new Comment("Find Comment");
        assertThat(comment.getId()).isNull();
        Comment savedComment = commentRepository.save(comment);

        // when
        Comment findComment = commentRepository.findById(savedComment.getId())
                            .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(findComment.getId()).isEqualTo(savedComment.getId());
    }

    @Test
    void invalidFindAll() {
        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isEmpty();
    }

    @Test
    void findByCommentContains() {
        // given
        createComment("Spring Data JPA", 100);
        createComment("Hibernate Spring", 55);
        createComment("Spring Data Common", 10);

        // when
        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("Spring", 10);

        // then
        assertThat(comments).hasSize(2);
    }

    @Test
    void findByCommentContainsIgnoreCaseOrderByLikeCountDesc() {
        // given
        createComment("Spring Data JPA", 100);
        createComment("Hibernate Spring", 55);
        createComment("Spring Data Common", 10);

        // when
        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("Spring");

        // then
        assertThat(comments).hasSize(3);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    @Test
    void findByCommentContainsIgnoreCaseReturnPage() {
        // given
        createComment("Spring Data JPA", 100);
        createComment("Hibernate Spring", 55);
        createComment("Spring Data Common", 10);

        // when
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "likeCount");
        Page<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);

        // then
        assertThat(comments.getNumberOfElements()).isEqualTo(3); // 현재 가지고 있는 컨텐츠 개수
        assertThat(comments.getTotalPages()).isEqualTo(1); // 현재 모든 페이지 개수 (size 가 10 인데 컨텐츠가 3개 있으니 1)
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    private void createComment(String commentDescription, int likeCount) {
        Comment comment = new Comment(commentDescription);
        comment.addLikeCount(likeCount);
        commentRepository.save(comment);
    }
}
