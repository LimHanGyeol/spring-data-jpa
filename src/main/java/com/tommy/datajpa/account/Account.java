package com.tommy.datajpa.account;

import com.tommy.datajpa.study.Study;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring Data JPA
 * Entity Type Mapping, 관계 맵핑, Cascade
 * Composite Type 을 n 개로 사용할 경우가 있다.
 * 이런 경우 AttributeOverrides({ AttributeOverride }) 를 사용하여 이름을 재정의 해준다.
 *
 * Set<Study> studies 를 단방향 관계 OneToMany 로 맵핑 했다.
 * 단방향일 경우 기본 설정은 관계 테이블을 만들어 id 를 관리한다.
 * 양방향으로 만들경우 주인이 아닌 Account 에서 mappedBy 를 정의해야 한다.
 * mappedBy = "owner" 은 Study 에서 정의한 Account 필드의 이름이다.
 *
 * Cascade : 엔티티의 상태 변화를 전파 시키는 옵션
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Transient // 해당 어노테이션을 설정하면 Column 으로 생성하지 않는다.
    private String rePassword;

    // Date, Calendar Type 을 매핑 해주는 날짜 어노테이션.
    // JPA 가 Java8 이 나오기 이전에 표준이 정의되어있던 상태여서 LocalDate.. 에 대한 어노테이션이 마련되지 않았다.
    // @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createAt;

    @Embedded // Composite 한 타입을 Field 로 사용할 경우
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
   private Address homeAddress;

    // 관계 테이블이 생성된다.
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies;

    public Account(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.createAt = LocalDateTime.now();
        this.homeAddress = address;
        this.studies = new HashSet<>();
    }

    public void addStudy(Study study) {
        this.studies.add(study);
        study.addOwner(this);
    }

    public void removeStudy(Study study) {
        this.studies.remove(study);
        study.removeOwner();
    }

    public void updateUsername(String username) {
        this.username = username;
    }

}
