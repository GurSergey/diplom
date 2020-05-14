package com.company.services;

import com.company.enitities.UserEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.dao.UserDAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    UserDAO dao;

    public static String hashedPassword(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public UserEntity getUserByLoginPassword(String login, String password) throws SelectException{
        return dao.getUserByLoginPassword(login, hashedPassword(password));
    }

    public void deleteUSer(UserEntity user) throws DeleteException {
         dao.deleteUser(user);
    }

//    public UserEntity getVoterById(int id) throws SelectException{
//        return repository.getVoterById(id);
//    }

//    public void updateUser(UserEntity user) throws UpdateException{
//        user.setPassword(hashedPassword(user.getPassword()));
//        dao.
//        dao.updateUser(user);
//    }

    public void createUser(UserEntity user) throws InsertException {
        user.setPassword(hashedPassword(user.getPassword()));
        dao.createUser(user);
    }

    public UserEntity[] getAllUsers() throws SelectException{
        return dao.getAllUsers();
    }
}
