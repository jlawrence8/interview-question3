package com.example.forum.model;

import lombok.Getter;

import javax.persistence.*;

/**
 * Entity class used to persist Forum Questions
 */
@Entity
@Table(name = "TBL_FORUM_QUESTION")
@Getter
public class ForumQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "MESSAGE")
    private String message;

    public ForumQuestionEntity() {
    }

    public ForumQuestionEntity(Long id) {
        this.id = id;
    }

    public ForumQuestionEntity(String author, String message) {
        this.author = author;
        this.message = message;
    }

}
