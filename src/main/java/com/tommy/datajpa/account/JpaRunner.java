package com.tommy.datajpa.account;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Spring Data JPA
 * Entity Type Mapping
 */
@Component
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager; // JPA 의 핵심적인 API

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account("hangyeol", "jpa", new Address());

        Session session = entityManager.unwrap(Session.class); // Hibernate 의 핵심적인 API = Session
        session.save(account);
    }
}
