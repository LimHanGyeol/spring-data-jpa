package com.tommy.datajpa.comment;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * Spring Data Common
 * 인터페이스 정의하기
 */
// @RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends BaseRepository<Comment, Long> {

    // Comment save(Comment comment);

    // List<Comment> findAll();

}
