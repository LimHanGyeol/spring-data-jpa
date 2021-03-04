package com.tommy.querydsl.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

//    @GetMapping("/posts/{id}")
//    public ResponseEntity<Post> getPost(@PathVariable Long id) {
//        return ResponseEntity.ok().body(postService.findById(id));
//    }

    /**
     * @param post DomainClassConverter 사용
     * @return post
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Post post) {
        return ResponseEntity.ok().body(postService.findById(post.getId()));
    }
}
