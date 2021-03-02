package com.tommy.datajpa.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.createAt = LocalDateTime.now();
    }

}
