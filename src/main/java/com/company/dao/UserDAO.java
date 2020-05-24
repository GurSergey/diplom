package com.company.dao;

import com.company.enitities.UserEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface UserDAO {
    UserEntity getUserByLoginPassword(String login, String password) throws SelectException;
    void createUser(UserEntity voter) throws InsertException;
    UserEntity[] getAllUsers() throws SelectException;
    void deleteUser(UserEntity user) throws DeleteException;
//    UserEntity getVoterById(int id) throws SelectException;
    UserEntity updateUser(UserEntity user) throws UpdateException;
}
