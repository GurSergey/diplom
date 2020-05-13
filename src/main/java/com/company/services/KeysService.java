package com.company.services;

import com.company.dao.KeysDAO;
import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public class KeysService {
    private KeysDAO dao;

    public KeysService(KeysDAO dao){
        this.dao = dao;
    }

    public KeyEntity[] getAllKeys() throws SelectException {
        return dao.getAllKeys();
    }

    public void saveKey(KeyEntity key) throws InsertException{
        dao.saveKey(key);
    }

    public void deleteKey(KeyEntity key) throws DeleteException {
        dao.deleteKey(key);
    }

    public void updateKey(KeyEntity key) throws UpdateException {
        dao.updateKey(key);
    }
}
