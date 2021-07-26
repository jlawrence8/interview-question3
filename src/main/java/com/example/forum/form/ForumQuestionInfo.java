package com.example.forum.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Info class used to provide response for the createQuestion API.
 */
@Getter
@EqualsAndHashCode
public class ForumQuestionInfo {

    private Long id;

    private String author;

    private String message;

    private long replies;

    /**
     * New instance of ForumQuestionInfo class will be created using this constructor only.
     * Setter methods are disabled to restrict accidental modification of object after creation.
     *
     * @param id      Question ID
     * @param author  Question Author
     * @param message Question message
     * @param replies Number of replies
     */
    public ForumQuestionInfo(Long id, String author, String message, long replies) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.replies = replies;
    }

}
