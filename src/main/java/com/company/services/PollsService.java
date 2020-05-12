package com.company.services;

import com.company.enitities.DatasetEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.dao.PollsDAO;

public class PollsService {
    PollsDAO repository;

    public PollsService(PollsDAO repository)
    {
        this.repository = repository;
    }

    public DatasetEntity[] getPolls() throws SelectException {
        return repository.getAllPolls();
    }

    public void updatePoll(DatasetEntity poll) throws UpdateException {
        repository.updatePoll(poll);
    }

    public void savePoll(DatasetEntity poll) throws InsertException {
        repository.savePoll(poll);
    }

    public void deletePoll(DatasetEntity poll) throws DeleteException {
        repository.deletePoll(poll);
    }

    public DatasetEntity[] getAllOpenPolls() throws SelectException{
        return repository.getAllOpenPolls();
    }

    public DatasetEntity[] getAllEndedPolls() throws SelectException{
        return repository.getAllEndedPolls();
    }

    public DatasetEntity getPollResult(int id) throws SelectException{
        return repository.getPollResult(id);
    }

    public DatasetEntity getPollWithAnswersUser(int pollId, int userId) throws SelectException{
        return repository.getPollWithAnswersUser(pollId, userId);
    }

    public DatasetEntity[] getPollsByUser(int userId) throws SelectException {
        return repository.getPollsByUser(userId);
    }

}
