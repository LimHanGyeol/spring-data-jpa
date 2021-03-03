package com.tommy.datajpa.post;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Spring Data JPA 활용
 * Domain Event
 */
@Getter
public class PostPublishedEvent extends ApplicationEvent {

    private final Post post;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public PostPublishedEvent(Object source) {
        super(source);
        this.post = (Post) source;
    }
}
