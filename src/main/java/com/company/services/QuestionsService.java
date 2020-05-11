package com.company.services;

import com.company.db.QuestionsRepositoryDB;
import com.company.enitities.QuestionEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.QuestionsRepository;
import com.company.repositories.VariantsRepository;

public class QuestionsService {
    private QuestionsRepository repository;

    public QuestionsService(QuestionsRepository questionsRepository) {
        this.repository = questionsRepository;
    }

    public QuestionEntity[] getQuestionsByIdPoll(int id) throws SelectException {
        return this.repository.getAllQuestionsByPollId(id);
    }

    public void saveQuestion(QuestionEntity question) throws InsertException {
        this.repository.saveQuestion(question);
    }

    public void updateQuestion(QuestionEntity question) throws UpdateException {
        this.repository.updateQuestion(question);
    }

    public void deleteQuestion(QuestionEntity question) throws DeleteException {
        this.repository.deleteQuestion(question);
    }

    public QuestionEntity getQuestionForUser(int pollId, int userId) throws SelectException{
        return this.repository.getQuestionForUser(pollId, userId);
    }
}
