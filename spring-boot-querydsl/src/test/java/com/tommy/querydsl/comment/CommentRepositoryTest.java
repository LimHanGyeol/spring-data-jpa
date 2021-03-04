package com.tommy.querydsl.comment;

import com.tommy.querydsl.post.Post;
import com.tommy.querydsl.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void getComment() {
        Post post = new Post("jpa");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment("comment");
        comment.setPost(savedPost);
        commentRepository.save(comment);

        Optional<Comment> byId = commentRepository.findById(1L);
        System.out.println(byId.get().getPost());
    }
}
