package com.example.forum.repository;

import com.example.forum.model.ForumReplyEntity;
import com.example.forum.model.ForumReplyMinimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository class for ForumReplyEntity. Normal JPA methods inherited from the super class.
 */
@Repository
public interface ForumReplyRepository
        extends JpaRepository<ForumReplyEntity, Long> {

    @Query("select new com.example.forum.model.ForumReplyMinimal(r.id, r.author, r.message) from ForumReplyEntity r where r.forumQuestionEntity.id=:questionId")
    List<ForumReplyMinimal> findQuestionReplies(long questionId);
}
