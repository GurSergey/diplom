package com.company.services;

import com.company.enitities.AnswerEntity;
import com.company.exceptions.InsertException;
import com.company.dao.DatasetDAO;

public class AnswerService {
    private DatasetDAO answerRepository;

    public AnswerService(DatasetDAO answerRepository){
        this.answerRepository = answerRepository;
    }

    public void saveAnswer(AnswerEntity answer) throws InsertException{
        answerRepository.saveAnswer(answer);
    }
}
