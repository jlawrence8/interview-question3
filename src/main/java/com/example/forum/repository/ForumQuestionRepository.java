package com.example.forum.repository;

import com.example.forum.model.ForumQuestionEntity;
import com.example.forum.model.ForumQuestionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository class for ForumQuestionEntity. Normal JPA methods inherited from the super class.
 */
@Repository
public interface ForumQuestionRepository
        extends JpaRepository<ForumQuestionEntity, Long> {

    @Query("select new com.example.forum.model.ForumQuestionSummary(q.id, q.author, q.message, count(r.id)) from ForumQuestionEntity q left join fetch ForumReplyEntity r on q.id=r.forumQuestionEntity.id group by q.id, q.author, q.message")
    List<ForumQuestionSummary> findAllQuestionsSummary();

}
