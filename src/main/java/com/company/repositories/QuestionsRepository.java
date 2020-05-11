package com.company.repositories;

import com.company.enitities.QuestionEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface QuestionsRepository {
    QuestionEntity[] getAllQuestionsByPollId(int pollId) throws SelectException;
    void saveQuestion(QuestionEntity question) throws InsertException;
    void updateQuestion(QuestionEntity question) throws UpdateException;
    void deleteQuestion(QuestionEntity question) throws DeleteException;
    QuestionEntity getQuestionForUser(int pollId, int userId) throws SelectException;
}
