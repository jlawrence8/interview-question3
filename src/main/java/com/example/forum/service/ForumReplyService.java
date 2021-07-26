package com.example.forum.service;

import com.example.forum.exception.RecordNotFoundException;
import com.example.forum.model.ForumReplyEntity;
import com.example.forum.repository.ForumReplyRepository;
import com.example.forum.form.ForumReplyForm;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * ForumReplyService class act as a service layer or business layer to support
 * the REST API controller to build business logic to deal with forum question replies.
 * Also, it acts as a layer to communicate to the persistence layer.
 */
@Service
public class ForumReplyService {

    private ForumReplyRepository repository;

    public ForumReplyService(ForumReplyRepository repository) {
        this.repository = repository;
    }

    /**
     * Service class to create a reply for the question.
     *
     * @param questionId Question ID
     * @param replyForm  ForumReplyForm - input to create a reply.
     * @return ForumReplyEntity - return value after creation of a reply.
     * @throws RecordNotFoundException - throws when record not found against questionId
     */
    public ForumReplyEntity createReply(Long questionId, ForumReplyForm replyForm) throws RecordNotFoundException {
        ForumReplyEntity newEntity;
        try {
            //Creating new entity and saving it
            newEntity = new ForumReplyEntity(questionId, replyForm.getAuthor(), replyForm.getMessage());
            newEntity = repository.save(newEntity);
        } catch (DataIntegrityViolationException e) {
            throw new RecordNotFoundException("No Question record exist for given id");
        }
        return newEntity;
    }

}
