package com.tommy.querydsl.book;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends CustomRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
}
