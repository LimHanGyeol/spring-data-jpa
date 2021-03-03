package com.tommy.querydsl;

import com.tommy.querydsl.book.Book;
import com.tommy.querydsl.book.BookRepository;
import com.tommy.querydsl.book.QBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void save() {
        // given
        Book book = new Book("spring", "data jpa");
        bookRepository.save(book);

        // when
        List<Book> books = bookRepository.findAll();

        // then
        assertThat(books).hasSize(1);
    }

    @Test
    void createByQueryDSL() {
        // given
        Book book = new Book("spring", "data jpa");
        bookRepository.save(book);

        // when
        Book ring = bookRepository.findOne(QBook.book.title.contains("ring"))
                .orElseThrow();

        Optional<Book> jpa = bookRepository.findOne(QBook.book.title.contains("jpa"));

        // then
        assertThat(ring).isNotNull();
        assertThat(jpa.isPresent()).isFalse();
    }

    @Test
    void contains() {
        // given
        Book book = new Book("spring", "data jpa");
        bookRepository.save(book);

        // when
        boolean result = bookRepository.contains(book);

        // then
        assertThat(result).isTrue();
    }
}
