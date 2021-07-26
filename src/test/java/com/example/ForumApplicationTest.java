package com.example;

import com.example.forum.controller.ForumController;
import com.example.forum.exception.RecordNotFoundException;
import com.example.forum.form.ForumQuestionForm;
import com.example.forum.form.ForumQuestionInfo;
import com.example.forum.form.ForumReplyForm;
import com.example.forum.form.ForumReplyInfo;
import com.example.forum.model.ForumQuestionSummary;
import com.example.forum.model.ForumQuestionReplies;
import com.example.forum.model.ForumReplyMinimal;
import com.example.forum.repository.ForumQuestionRepository;
import com.example.forum.repository.ForumReplyRepository;
import com.example.forum.service.ForumQuestionService;
import com.example.forum.service.ForumReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class does the integration testing for the Forum application.
 */
@SpringBootTest
class ForumApplicationTest {

    @Autowired
    private ForumQuestionService questionService;
    @Autowired
    private ForumReplyService replyService;
    @Autowired
    private ForumController forumController;
    @Autowired
    private ForumQuestionRepository forumQuestionRepository;
    @Autowired
    private ForumReplyRepository forumReplyRepository;

    /**
     * Testing the application context loading.
     */
    @Test
    void contextLoads() {
    }

    /**
     * Test method added ONLY to cover main() invocation not covered by application tests.
     */
    @Test
    void applicationStartTest() {
        ForumApplication.main(new String[]{});
    }

    /**
     * This single test cover the below flows as part of the integration tests
     * 1. Test whether getAllQuestions returns an empty list if there are no questions in the forum.
     * 2. Test creation of a question.
     * 3. Test creation of a reply to the question.
     * 4. Test fetching of all the replies of a particular question.
     * 5. Test fetching of all the questions.
     * 6. Test usage of invalid question ID to store a reply to the question.
     * 7. Test usage of invalid question ID to fetch question replies.
     *
     * @throws RecordNotFoundException - used to cover the throwing from createReply method.
     */
    @Test
    void createQuestion() throws RecordNotFoundException {

        //Test getAllQuestions Empty list
        ResponseEntity<List<ForumQuestionSummary>> allQuestionEmptyEntity = forumController.getAllQuestions();
        assertThat(allQuestionEmptyEntity.getStatusCode().equals(HttpStatus.OK));
        List<ForumQuestionSummary> questionEmptyList = allQuestionEmptyEntity.getBody();
        assertThat(questionEmptyList).isEqualTo(new ArrayList<ForumQuestionSummary>());

        //Testing Create Question
        ForumQuestionForm questionForm = new ForumQuestionForm();
        questionForm.setAuthor("author1");
        questionForm.setMessage("message1");
        ResponseEntity<ForumQuestionInfo> questionResponseEntity = forumController.createQuestion(questionForm);
        assertThat(questionResponseEntity.getStatusCode().equals(HttpStatus.CREATED));
        ForumQuestionInfo forumQuestionInfo = questionResponseEntity.getBody();
        assertThat(forumQuestionInfo).isEqualTo(new ForumQuestionInfo(1L, "author1", "message1", 0));

        //Testing Create Reply
        ForumReplyForm replyForm = new ForumReplyForm();
        replyForm.setAuthor("reply author1");
        replyForm.setMessage("reply message1");
        ResponseEntity<ForumReplyInfo> replyResponseEntity = forumController.createReply(1L, replyForm);
        assertThat(replyResponseEntity.getStatusCode().equals(HttpStatus.CREATED));
        ForumReplyInfo forumReplyInfo = replyResponseEntity.getBody();
        assertNotNull(forumQuestionInfo);
        assertThat(forumReplyInfo).isEqualTo(new ForumReplyInfo(forumQuestionInfo.getId(), 1L, "reply author1", "reply message1"));

        //Test fetching of Question Reply
        ResponseEntity<ForumQuestionReplies> questionReplyResponseEntity = forumController.getQuestionById(1L);
        assertThat(questionReplyResponseEntity.getStatusCode().equals(HttpStatus.OK));
        ForumQuestionReplies forumQuestionReplies = questionReplyResponseEntity.getBody();
        ForumReplyMinimal forumReplyMinimal = new ForumReplyMinimal(1L, "reply author1", "reply message1");
        List<ForumReplyMinimal> forumReplyMinimalList = new ArrayList<>();
        forumReplyMinimalList.add(forumReplyMinimal);
        assertThat(forumQuestionReplies).isEqualTo(new ForumQuestionReplies(1L, "author1", "message1", forumReplyMinimalList));

        //Test fetching of all the Questions
        ResponseEntity<List<ForumQuestionSummary>> allQuestionResponseEntity = forumController.getAllQuestions();
        assertThat(questionReplyResponseEntity.getStatusCode().equals(HttpStatus.OK));
        List<ForumQuestionSummary> questionList = allQuestionResponseEntity.getBody();
        List<ForumQuestionSummary> expectedQuesList = new ArrayList<>();
        ForumQuestionSummary forumQuestionSummary = new ForumQuestionSummary(1L, "author1", "message1", 1L);
        expectedQuesList.add(forumQuestionSummary);
        assertThat(questionList).isEqualTo(expectedQuesList);

        //Test Exception scenario
        Exception exception = assertThrows(RecordNotFoundException.class, () -> replyService.createReply(99L, replyForm));

        //Test Question Reply with invalid question number
        ResponseEntity<ForumQuestionReplies> replyNotFoundEntity = forumController.getQuestionById(99L);
        assertThat(replyNotFoundEntity.getStatusCode().equals(HttpStatus.NO_CONTENT));
        ForumQuestionReplies forumQuestionRepliesNotFound = replyNotFoundEntity.getBody();
        assertNull(forumQuestionRepliesNotFound);

    }
}
