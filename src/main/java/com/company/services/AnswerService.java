package com.company.services;

import com.company.enitities.AnswerEntity;
import com.company.exceptions.InsertException;
import com.company.repositories.AnswerRepository;

public class AnswerService {
    private AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public void saveAnswer(AnswerEntity answer) throws InsertException{
        answerRepository.saveAnswer(answer);
    }
}
