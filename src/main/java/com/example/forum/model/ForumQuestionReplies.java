package com.example.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * This class provides relevant data for getQuestionById API in the required format.
 */
@Getter
@EqualsAndHashCode
public class ForumQuestionReplies {

    private Long id;

    private String author;

    private String message;

    private List<ForumReplyMinimal> replies;

    public ForumQuestionReplies(Long id, String author, String message, List<ForumReplyMinimal> replies) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.replies = replies;
    }

}
