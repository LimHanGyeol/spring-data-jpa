package com.tommy.datajpa.post;

import java.util.List;

/**
 * Spring Data JPA 활용
 * CustomRepository 만들기
 */
public interface PostCustomRepository<T> {

    List<Post> findMyPost();

    void delete(T entity);

}
