package com.example.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * This class provides relevant data for getAllQuestions API in the required format.
 */
@Getter
@EqualsAndHashCode
public class ForumQuestionSummary {

    private Long id;

    private String author;

    private String message;

    private long replies;

    public ForumQuestionSummary(Long id, String author, String message, Long replies) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.replies = (replies == null ? 0 : replies);
    }

}
