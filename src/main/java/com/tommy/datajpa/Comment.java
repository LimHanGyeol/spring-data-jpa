package com.tommy.datajpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Spring Data JPA
 * 엔티티 상태와 Cascade, Fetch
 *
 * Cascade 는 이러한 상태 변화를 전이 시키는 것이다.
 * Cascade 는 ManyToOne 이런 관계 상태에서 사용되는 것이 아니라
 * 도메인의 상태. Parent 냐 Child 냐의 관계에서 사용한다.
 * 그에대한 좋은 예시가 Post - Comment 관계 이다.
 *
 * Fetch 는 연관 관계에 있는 Entity 의 데이터를 어떻게 가져올지에 대한 설정이다.
 * Entity 를 가져올 때 연관 관계에 있는 Entity 도 지금 가져올 것이냐 ? (Eager)
 * Entity 를 가져올 때 연관 관계에 있는 Entity 를 나중에 가져올 것이냐 ? (Lazy)
 * ManyToOne 의 기본값은 Eager 이다.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    public Comment(String comment) {
        this.comment = comment;
    }

    public void dependentOnPost(Post post) {
        this.post = post;
    }
}
