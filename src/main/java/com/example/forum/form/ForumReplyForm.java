package com.example.forum.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Form class used to collect input for createReply API.
 */
@Setter
@Getter
public class ForumReplyForm {

    @NotNull
    private String author;

    @NotNull
    private String message;

}
