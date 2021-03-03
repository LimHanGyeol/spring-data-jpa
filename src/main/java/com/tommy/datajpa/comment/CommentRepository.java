package com.tommy.datajpa.comment;

import com.tommy.datajpa.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * Spring Data Common
 * 인터페이스 정의하기, 쿼리
 * Query 어노테이션이 붙어 있을 경우 해당 sql 을 우선한다.
 */
// @RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends BaseRepository<Comment, Long> {

    // Comment save(Comment comment);

    // List<Comment> findAll();

    // @Query(value = "SELECT c FROM Comment AS c", nativeQuery = true)
    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

    // 쿼리 만들기 실습
    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

}
