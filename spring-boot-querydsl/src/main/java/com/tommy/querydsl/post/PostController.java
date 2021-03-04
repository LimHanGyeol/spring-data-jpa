package com.tommy.querydsl.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Data JPA
 * Spring Data Common. DomainClassConverter, Pageable & Sort, HATEOAS
 */
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

    /**
     * Spring Boot 2.2 이상에서 Resource, PagedResource 가 PagedModel 로 변경됨.
     * @param pageable
     * @param assembler Entity 타입의 Resource 를 만들어 준다.
     * @return
     */
    @GetMapping("/posts")
    public PagedModel<EntityModel<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler) {
        Page<Post> posts = postService.findAll(pageable);
        return assembler.toModel(posts);
    }
}
