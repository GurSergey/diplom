package com.company.repositories;

import com.company.enitities.VoterEntity;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface UserRepository {
    VoterEntity getUserByLoginPassword(String login, String password) throws SelectException;
    void createUser(VoterEntity voter) throws InsertException;
    VoterEntity[] getAllUsers() throws SelectException;
    VoterEntity getVoterById(int id) throws SelectException;
    public VoterEntity updateVoter(VoterEntity voter) throws UpdateException;
}
