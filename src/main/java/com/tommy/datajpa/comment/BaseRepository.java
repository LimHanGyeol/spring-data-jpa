package com.tommy.datajpa.comment;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Spring Data Common
 * 인터페이스 정의하기
 */
@NoRepositoryBean
public interface BaseRepository<T, Id extends Serializable> extends Repository<T, Id> {

    <E extends T> E save(E entity);

    List<T> findAll();

    long count();

}
