package com.example.forum.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Info class used to provide response for the createReply API.
 */
@Getter
@EqualsAndHashCode
public class ForumReplyInfo {

    private Long questionId;

    private Long id;

    private String author;

    private String message;

    /**
     * New instance of ForumReplyInfo class will be created using this constructor only.
     * Setter methods are disabled to restrict accidental modification of object after creation.
     *
     * @param questionId Question ID
     * @param id         Reply ID
     * @param author     Reply Author
     * @param message    Reply Message
     */
    public ForumReplyInfo(Long questionId, Long id, String author, String message) {
        this.questionId = questionId;
        this.id = id;
        this.author = author;
        this.message = message;
    }

}
