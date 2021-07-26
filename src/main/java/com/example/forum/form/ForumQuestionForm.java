package com.example.forum.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Form class used to collect input for createQuestion API.
 */
@Setter
@Getter
public class ForumQuestionForm {

    @NotNull
    private String author;

    @NotNull
    private String message;

}
