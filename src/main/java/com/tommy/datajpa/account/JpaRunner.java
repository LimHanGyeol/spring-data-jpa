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
 * 양방향 매핑에서 관계 설정은 주인 엔티티가 해야 한다.
 * Account 가 아닌 Study 에서 관계 설정을 해야 ForeignKey 가 제대로 설정이 된다.
 * Account 에 Set<Study> studies 를 만들고 주입하면 관계가 맺어지지 않는다.
 */
@Component
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager; // JPA 의 핵심적인 API

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Address address = new Address("해밀예당 3로 38", "경기도", "남양주시", "12569");
        Account account = new Account("hangyeol", "jpa", address);

        // account 에 Study 를 추가하는 코드를 삭제하고 실행하더라도 관계 맵핑에는 문제가 없다.
        // 하지만 객체지향적으로는 두 객체가 양방향 참조를 하고 있기 때문에 서로 참조를 해야 한다.
        // 각각 객체의 상태를 불러 참조하는 것이 아닌, 메세지를 보내 처리하게 했다.
        Study study = new Study("Spring Data JPA");
        account.addStudy(study);

        Session session = entityManager.unwrap(Session.class); // Hibernate 의 핵심적인 API = Session
        session.save(account);
        session.save(study);
    }
}
