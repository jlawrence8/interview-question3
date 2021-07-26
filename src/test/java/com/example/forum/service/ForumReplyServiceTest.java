package com.example.forum.service;

import com.example.forum.exception.RecordNotFoundException;
import com.example.forum.form.ForumReplyForm;
import com.example.forum.model.ForumReplyEntity;
import com.example.forum.repository.ForumReplyRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class does unit testing of the ForumReplyService class.
 * Mockito is used to do the repository injections
 * and then these repositories are passed through a constructor to facilitate the unit test.
 */
class ForumReplyServiceTest {

    private ForumReplyRepository repository = mock(ForumReplyRepository.class);
    private ForumReplyService forumReplyService = new ForumReplyService(repository);

    /**
     * This unit test the service method forumReplyService.createReply and make sure the flow goes till the end of the class.
     * Alternative flows are covered as part of integration test.
     *
     * @throws RecordNotFoundException - used to cover the throwing from createReply method.
     */
    @Test
    void createReply() throws RecordNotFoundException {
        ForumReplyEntity forumReplyEntityExpected = new ForumReplyEntity(1L, "author1", "message1");
        doReturn(forumReplyEntityExpected).when(repository).save(any());

        ForumReplyForm forumReplyForm = new ForumReplyForm();
        forumReplyForm.setAuthor("author1");
        forumReplyForm.setMessage("message1");
        ForumReplyEntity forumReplyEntity = forumReplyService.createReply(1L, forumReplyForm);
        assertNotNull(forumReplyEntity);
        assertThat(forumReplyEntity).isEqualTo(forumReplyEntityExpected);
    }
}