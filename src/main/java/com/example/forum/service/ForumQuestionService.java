package com.example.forum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.forum.model.ForumQuestionEntity;
import com.example.forum.model.ForumQuestionSummary;
import com.example.forum.model.ForumQuestionReplies;
import com.example.forum.repository.ForumQuestionRepository;
import com.example.forum.form.ForumQuestionForm;
import com.example.forum.repository.ForumReplyRepository;
import org.springframework.stereotype.Service;

/**
 * ForumQuestionService class act as a service layer or business layer to support
 * the REST API controller to build business logic to deal with forum Questions.
 * Also, it acts as a layer to communicate to the persistence layer.
 */
@Service
public class ForumQuestionService {

    private ForumQuestionRepository forumQuestionRepository;

    private ForumReplyRepository forumReplyRepository;

    /**
     * Using constructor injection.
     *
     * @param forumQuestionRepository ForumQuestionRepository parameter
     * @param forumReplyRepository    ForumReplyRepository parameter
     */
    public ForumQuestionService(ForumQuestionRepository forumQuestionRepository, ForumReplyRepository forumReplyRepository) {
        this.forumQuestionRepository = forumQuestionRepository;
        this.forumReplyRepository = forumReplyRepository;
    }

    /**
     * Service class to create a question.
     *
     * @param questionForm ForumQuestionForm - input to create a question.
     * @return ForumQuestionEntity
     */
    public ForumQuestionEntity createQuestion(ForumQuestionForm questionForm) {
        //Creating new entity and saving it
        ForumQuestionEntity newEntity = new ForumQuestionEntity(questionForm.getAuthor(), questionForm.getMessage());
        return forumQuestionRepository.save(newEntity);
    }

    /**
     * Service class to get replies for a particular question.
     *
     * @param id Question ID
     * @return ForumQuestionReplies - Question replies
     */
    public ForumQuestionReplies getQuestionReplies(Long id) {
        ForumQuestionReplies forumQuestionReplies = null;
        Optional<ForumQuestionEntity> forumQuestion = forumQuestionRepository.findById(id);
        if (forumQuestion.isPresent()) {
            ForumQuestionEntity forumQuestionEntity = forumQuestion.get();
            //finding all replies for the question
            forumQuestionReplies = new ForumQuestionReplies(forumQuestionEntity.getId(), forumQuestionEntity.getAuthor(),
                    forumQuestionEntity.getMessage(), forumReplyRepository.findQuestionReplies(forumQuestionEntity.getId()));
        }
        return forumQuestionReplies;
    }

    /**
     * Service class to fetch all questions from the forum.
     *
     * @return List of ForumQuestionSummary - return summary of all Questions.
     */
    public List<ForumQuestionSummary> getAllQuestions() {
        List<ForumQuestionSummary> questionList = forumQuestionRepository.findAllQuestionsSummary();
        if (questionList.size() > 0) {
            return questionList;
        } else {
            return new ArrayList<>();
        }
    }

}
