package com.example.forum.controller;

import com.example.forum.exception.RecordNotFoundException;
import com.example.forum.form.ForumQuestionInfo;
import com.example.forum.form.ForumReplyInfo;
import com.example.forum.model.ForumQuestionEntity;
import com.example.forum.model.ForumQuestionSummary;
import com.example.forum.model.ForumQuestionReplies;
import com.example.forum.model.ForumReplyEntity;
import com.example.forum.service.ForumQuestionService;
import com.example.forum.service.ForumReplyService;
import com.example.forum.form.ForumQuestionForm;
import com.example.forum.form.ForumReplyForm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This class provides Rest support for the Forum Application.
 */
@RestController
@RequestMapping("/questions")
@Validated
public class ForumController {

    private ForumQuestionService questionService;
    private ForumReplyService replyService;

    /**
     * Uses constructor injection to load both ForumQuestionService and ForumReplyService.
     *
     * @param questionService ForumQuestionService
     * @param replyService    ForumReplyService
     */
    public ForumController(ForumQuestionService questionService, ForumReplyService replyService) {
        this.questionService = questionService;
        this.replyService = replyService;
    }

    /**
     * This API creates Questions for the forum.
     *
     * @param questionForm This is used to restrict and validate the input. @Valid annotation will validate the input questionForm.
     * @return ResponseEntity type of ForumQuestionInfo - return value after creation of a question.
     */
    @PostMapping
    public ResponseEntity<ForumQuestionInfo> createQuestion(@Valid @RequestBody ForumQuestionForm questionForm) {
        ForumQuestionEntity createdEntity = questionService.createQuestion(questionForm);
        // formatting the result to make it presentable
        ForumQuestionInfo questionInfo = new ForumQuestionInfo(createdEntity.getId(), createdEntity.getAuthor(), createdEntity.getMessage(), 0);
        return new ResponseEntity<>(questionInfo, new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * This API creates a Reply for the Questions.
     *
     * @param questionId Question ID for which reply will be created.
     * @param replyForm  This is used to restrict and validate the input. @Valid annotation will validate the input replyForm.
     * @return ResponseEntity type of ForumReplyInfo - return value after creation of a reply.
     * @throws RecordNotFoundException - Throws when record not found for the questionId.
     */
    @PostMapping("/{questionId}/reply")
    public ResponseEntity<ForumReplyInfo> createReply(@PathVariable("questionId") Long questionId, @Valid @RequestBody ForumReplyForm replyForm) throws RecordNotFoundException {
        ForumReplyEntity createdEntity = replyService.createReply(questionId, replyForm);
        // formatting the result to make it presentable
        ForumReplyInfo replyInfo = new ForumReplyInfo(createdEntity.getForumQuestionEntity().getId(), createdEntity.getId(), createdEntity.getAuthor(), createdEntity.getMessage());
        return new ResponseEntity<>(replyInfo, new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * This API fetches all the replies for a particular question.
     *
     * @param questionId Question ID using which the replies will be fetched.
     * @return ResponseEntity type of ForumQuestionReplies - return value contains replies for the questions.
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<ForumQuestionReplies> getQuestionById(@PathVariable("questionId") Long questionId) {
        ForumQuestionReplies forumQuestionReplies = questionService.getQuestionReplies(questionId);
        if (forumQuestionReplies == null) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(forumQuestionReplies, new HttpHeaders(), HttpStatus.OK);
        }
    }

    /**
     * This API fetches all the questions. This method is not using pagination.
     * Further enhancement will be required if we need to do so.
     *
     * @return ResponseEntity List of ForumQuestionSummary - contains summary of all the questions.
     */
    @GetMapping
    public ResponseEntity<List<ForumQuestionSummary>> getAllQuestions() {
        List<ForumQuestionSummary> list = questionService.getAllQuestions();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
