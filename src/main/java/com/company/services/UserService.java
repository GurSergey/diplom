package com.company.services;

import com.company.enitities.VoterEntity;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.UserRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    UserRepository repository;

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

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public VoterEntity getUserByLoginPassword(String login, String password) throws SelectException{
        return repository.getUserByLoginPassword(login, hashedPassword(password));
    }

    public VoterEntity getVoterById(int id) throws SelectException{
        return repository.getVoterById(id);
    }

    public void updateVoter(VoterEntity voter) throws UpdateException{
        voter.setPassword(hashedPassword(voter.getPassword()));
        repository.updateVoter(voter);
    }

    public void createUser(VoterEntity voter) throws InsertException {
        voter.setPassword(hashedPassword(voter.getPassword()));
        repository.createUser(voter);
    }

    public VoterEntity[] getAllUsers() throws SelectException{
        return repository.getAllUsers();
    }
}
