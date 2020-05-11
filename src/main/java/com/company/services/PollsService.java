package com.company.services;

import com.company.db.DBConnection;
import com.company.enitities.PollEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.PollsRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PollsService {
    PollsRepository repository;

    public PollsService(PollsRepository repository)
    {
        this.repository = repository;
    }

    public PollEntity[] getPolls() throws SelectException {
        return repository.getAllPolls();
    }

    public void updatePoll(PollEntity poll) throws UpdateException {
        repository.updatePoll(poll);
    }

    public void savePoll(PollEntity poll) throws InsertException {
        repository.savePoll(poll);
    }

    public void deletePoll(PollEntity poll) throws DeleteException {
        repository.deletePoll(poll);
    }

    public PollEntity[] getAllOpenPolls() throws SelectException{
        return repository.getAllOpenPolls();
    }

    public PollEntity[] getAllEndedPolls() throws SelectException{
        return repository.getAllEndedPolls();
    }

    public PollEntity getPollResult(int id) throws SelectException{
        return repository.getPollResult(id);
    }

    public PollEntity getPollWithAnswersUser(int pollId, int userId) throws SelectException{
        return repository.getPollWithAnswersUser(pollId, userId);
    }

    public PollEntity[] getPollsByUser(int userId) throws SelectException {
        return repository.getPollsByUser(userId);
    }

}
