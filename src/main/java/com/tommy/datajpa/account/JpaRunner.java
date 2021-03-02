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
 *
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

        // 1차 캐시
        Account hangyeol = session.load(Account.class, account.getId());
        // 객체의 변경상태를 계속해서 감지하는 Dirty Checking.
        // 45 라인만 호출될 경우 update 쿼리를 호출하지 않았는데 호출이 된다.
        hangyeol.updateUsername("tommy");

        // 객체 상태의 변화를 DB에 최대한 늦게. 가장 필요한 시점에 적용하는 Write Behind
        // 객체의 상태가 처음과 달라지지 않은 것을 알고, update 를 하지 않는다.
        hangyeol.updateUsername("dlagksruf");
        hangyeol.updateUsername("hangyeol");

        System.out.println("=====================");
        System.out.println(hangyeol.getUsername());
        // 트랜잭션이 끝나서 밖에서 사용이 될 때 Detached 상태가 된다.
        // 다시 Persistent 상태로 만들려면 ReDetached 상태로 만들면 된다.
        // Persistent 상태의 객체를 session.delete() 하면 객체의 상태는 Removed 가 된다.
        // Removed 도 JPA 가 관리하는 상태인데 이 상태에서는 실제 커밋이 일어날 때 삭제가 된다.
        // Cascade 는 이러한 상태 변화를 전이 시키는 것이다.
        // Cascade 는 ManyToOne 이런 관계 상태에서 사용되는 것이 아니라
        // 도메인의 상태. Parent 냐 Child 냐의 관계에서 사용한다.
    }
}
