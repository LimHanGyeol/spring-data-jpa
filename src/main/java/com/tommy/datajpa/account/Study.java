package com.tommy.datajpa.account;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Spring Data JPA
 * 관계 맵핑
 * ManyToOne 의 기본 설정은 예제와 같이 사용할 경우 Account 의 id 가 ForeignKey 로 사용된다.
 * 양방향 매핑에서 관계 설정은 주인 엔티티가 해야 한다.
 * Account 가 아닌 Study 에서 관계 설정을 해야 ForeignKey 가 제대로 설정이 된다.
 */
@NoArgsConstructor
@Getter
@Entity
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

     @ManyToOne
     private Account owner;

    public Study(String name) {
        this.name = name;
    }

    public void addOwner(Account account) {
        this.owner = account;
    }

    public void removeOwner() {
        this.owner = null;
    }

}
