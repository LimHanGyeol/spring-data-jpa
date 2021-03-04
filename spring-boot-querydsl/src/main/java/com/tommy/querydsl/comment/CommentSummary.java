package com.tommy.querydsl.comment;

import org.springframework.beans.factory.annotation.Value;

/**
 * Spring Data JPA
 * JPA. Projection
 * Closed Projection - Interface
 *
 * Open Projection 은 Value(SpEL) 를 사용하여 연산할 수 있다.
 * getVotes 의 경우 2개의 Attributes 를 가져온다. Attributes 를 알고 있어서 Comment 에서 가져올 수 있다.
 * 여기서 target 은 Comment 이다. Comment 를 전부 다 가져올 수 밖에 없다.
 * 가져올 Attribute 를 한정짓지 못해서 Open Projection 이라고 부른다.
 * Open Projection 하는 순간 성능 최적화는 물건너 갔고, 문자열 상태로 데이터를 가져올 수 있다.
 */
public interface CommentSummary {

    String getComment();

    int getUp();

    int getDown();

    /**
     * Open Projection 의 장점을 살리면서 한정적으로 데이터를 가지고 오고 싶다.
     * Java8 에서 추가된 default 메서드를 이용하면 Custom 한 방법으로 method 를 추가할 수 있고,
     * 사용할 수 있는 필드는 한정적으로 만들 수 있다.
     */
    default String getVotes() {
        return getUp() + " " + getDown();
    }

    // @Value("#{target.up + ' ' + target.down}")
    // String getVotes();

}
