package com.company.repositories;

import com.company.enitities.AnswerEntity;
import com.company.enitities.VoterEntity;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;

public interface AnswerRepository {
    void saveAnswer(AnswerEntity answer) throws InsertException;
}
