package com.example.forum.model;

import lombok.Getter;

import javax.persistence.*;

/**
 * Entity class used to persist Forum Replies
 */
@Entity
@Table(name = "TBL_FORUM_REPLY")
@Getter
public class ForumReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ForumQuestionEntity forumQuestionEntity;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "MESSAGE")
    private String message;

    public ForumReplyEntity() {
    }

    public ForumReplyEntity(Long questionId, String author, String message) {
        this.forumQuestionEntity = new ForumQuestionEntity(questionId);
        this.author = author;
        this.message = message;
    }

}
