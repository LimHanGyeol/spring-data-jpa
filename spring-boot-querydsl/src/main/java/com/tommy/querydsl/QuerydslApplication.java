package com.tommy.querydsl;

import com.tommy.querydsl.book.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// TODO : JPA Specifications 와 Query By Example 은 지금 당장 와닿지 않아서 타이핑 하며 보진 않음.
//  사용하게 될 때 다시 한번 보기
@EnableJpaAuditing(auditorAwareRef = "accountAuditAware")
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
@SpringBootApplication
public class QuerydslApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerydslApplication.class, args);
    }

}
