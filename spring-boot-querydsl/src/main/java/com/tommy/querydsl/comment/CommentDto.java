package com.tommy.querydsl.comment;

import lombok.Getter;

@Getter
public class CommentDto {

    private final String comment;

    private final int up;

    private final int down;

    public CommentDto(String comment, int up, int down) {
        this.comment = comment;
        this.up = up;
        this.down = down;
    }

    public String getVotes() {
        return getUp() + " " + getDown();
    }
}
