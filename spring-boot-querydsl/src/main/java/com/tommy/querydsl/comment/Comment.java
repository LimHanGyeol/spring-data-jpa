package com.tommy.querydsl.comment;

import com.tommy.querydsl.account.Account;
import com.tommy.querydsl.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Spring Data JPA
 * JPA. EntityGraph
 * NamedEntityGraph -> Entity 에서 재사용할 여러 엔티티 그룹을 정의할 때 사용
 */
@NamedEntityGraph(name = "Comment.post",
        attributeNodes = @NamedAttributeNode("post"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private int up;

    private int down;

    private boolean best;

    @CreatedDate
    private LocalDate createdAt;

    @CreatedBy
    @ManyToOne
    private Account createdBy;

    @LastModifiedDate
    private LocalDate updatedAt;

    @LastModifiedBy
    @ManyToOne
    private Account updatedBy;

    public Comment(String comment) {
        this.comment = comment;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public CommentDto toCommentDto() {
        return new CommentDto(comment, up, down);
    }
}
