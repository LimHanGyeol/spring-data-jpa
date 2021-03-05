package com.tommy.querydsl.comment;

import com.tommy.querydsl.post.Post;
import com.tommy.querydsl.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void getComment() {
        Post savedPost = createPost();

        Comment comment = new Comment("comment");
        comment.setPost(savedPost);
        commentRepository.save(comment);

        Optional<Comment> byId = commentRepository.findById(1L);
        System.out.println(byId.get().getPost());
    }

    @Test
    void findPostByCommentId() {
        Post savedPost = createPost();

        Comment comment = new Comment("comment");
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

//        CommentDto commentDtos = commentRepository.getById(1l).orElseThrow().toCommentDto();
//        System.out.println(commentDtos.getComment());

        List<CommentDto> byPostId = commentRepository.findByPostId(savedPost.getId());
        byPostId.forEach(c -> {
            System.out.println("==============");
            System.out.println(c.getVotes());
        });
    }

    private Post createPost() {
        Post post = new Post("jpa");
        return postRepository.save(post);
    }
}
