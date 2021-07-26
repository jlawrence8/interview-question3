package com.example.forum.service;

import com.example.forum.form.ForumQuestionForm;
import com.example.forum.model.ForumQuestionEntity;
import com.example.forum.model.ForumQuestionReplies;
import com.example.forum.model.ForumQuestionSummary;
import com.example.forum.repository.ForumQuestionRepository;
import com.example.forum.repository.ForumReplyRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

/**
 * This class does unit testing of the ForumQuestionService class.
 * Mockito is used to do the repository injections
 * and then these repositories are passed through a constructor to facilitate the unit test.
 */
class ForumQuestionServiceTest {

    private ForumQuestionRepository forumQuestionRepository = mock(ForumQuestionRepository.class);
    private ForumReplyRepository forumReplyRepository = mock(ForumReplyRepository.class);
    private ForumQuestionService forumQuestionService = new ForumQuestionService(forumQuestionRepository, forumReplyRepository);

    /**
     * This unit test the service method forumQuestionService.createQuestion and make sure the flow goes till the end of the class.
     * Alternative flows are covered as part of integration test.
     */
    @Test
    void createQuestion() {
        ForumQuestionEntity forumReplyEntityExpected = new ForumQuestionEntity("author1", "message1");
        doReturn(forumReplyEntityExpected).when(forumQuestionRepository).save(any());

        ForumQuestionForm questionForm = new ForumQuestionForm();
        questionForm.setAuthor("author1");
        questionForm.setMessage("message1");
        ForumQuestionEntity forumQuestionEntity = forumQuestionService.createQuestion(questionForm);
        assertNotNull(forumQuestionEntity);
        assertThat(forumQuestionEntity).isEqualTo(forumReplyEntityExpected);
    }

    /**
     * This unit test the service method forumQuestionService.getQuestionReplies and make sure the flow goes till the end of the class.
     * Alternative flows are covered as part of integration test.
     */
    @Test
    void getQuestionReplies() {
        ForumQuestionEntity forumQuestionEntityExpected = new ForumQuestionEntity(1L);
        ForumQuestionReplies forumQuestionRepliesExpected = new ForumQuestionReplies(1L, null, null, null);
        doReturn(Optional.of(forumQuestionEntityExpected)).when(forumQuestionRepository).findById(any());
        doReturn(null).when(forumReplyRepository).findQuestionReplies(anyLong());
        ForumQuestionReplies forumQuestionReplies = forumQuestionService.getQuestionReplies(1L);
        assertNotNull(forumQuestionReplies);
        assertThat(forumQuestionReplies).isEqualTo(forumQuestionRepliesExpected);
    }

    /**
     * This unit test the service method forumQuestionService.getAllQuestions and make sure the flow goes till the end of the class.
     * Alternative flows are covered as part of integration test.
     */
    @Test
    void getAllQuestions() {
        List<ForumQuestionSummary> forumQuestionSummaryListExpectd = new ArrayList<>();
        ForumQuestionSummary forumQuestionSummaryExpected = new ForumQuestionSummary(1L, "author1", "message1", 1L);
        forumQuestionSummaryListExpectd.add(forumQuestionSummaryExpected);
        doReturn(forumQuestionSummaryListExpectd).when(forumQuestionRepository).findAllQuestionsSummary();
        List<ForumQuestionSummary> forumQuestionSummaryList = forumQuestionService.getAllQuestions();
        assertNotNull(forumQuestionSummaryList);
        assertThat(forumQuestionSummaryList).isEqualTo(forumQuestionSummaryListExpectd);
    }
}