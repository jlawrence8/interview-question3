package com.example.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * This class provides relevant data for getQuestionById API in the required format.
 * This class has minimal information that is required for the question reply list.
 */
@Getter
@EqualsAndHashCode
public class ForumReplyMinimal {

    private Long id;

    private String author;

    private String message;

    public ForumReplyMinimal(Long id, String author, String message) {
        this.id = id;
        this.author = author;
        this.message = message;
    }

}
