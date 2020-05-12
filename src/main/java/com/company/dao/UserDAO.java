package com.company.dao;

import com.company.enitities.UserEntity;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;

public interface UserDAO {
    UserEntity getUserByLoginPassword(String login, String password) throws SelectException;
    void createUser(UserEntity voter) throws InsertException;
    UserEntity[] getAllUsers() throws SelectException;
//    UserEntity getVoterById(int id) throws SelectException;
//    UserEntity updateVoter(UserEntity voter) throws UpdateException;
}
