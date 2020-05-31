package com.company.services;

import com.company.dao.KeysDAO;
import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.session.ApiKeysStorage;

public class KeysService {
    private KeysDAO dao;

//    private static ApiKeysStorage keysStorage = new ApiKeysStorage();
    public KeysService(KeysDAO dao){
        this.dao = dao;
        if (!ApiKeysStorage.isIsInit())
        {
            try {
                KeyEntity[] keyEntities = this.getAllKeys();
                for (KeyEntity key: keyEntities) {
                    ApiKeysStorage.addKey(key.getKeyStr());
                }
            } catch (SelectException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean checkKey(String key){
        return ApiKeysStorage.containsKey(key);
    }

    public KeyEntity[] getAllKeys() throws SelectException {
        return dao.getAllKeys();
    }

    public void saveKey(KeyEntity key) throws InsertException{
        dao.saveKey(key);
        ApiKeysStorage.addKey(key.getKeyStr());
    }

    public void deleteKey(KeyEntity key) throws DeleteException {
        dao.deleteKey(key);
        ApiKeysStorage.removeKey(key.getKeyStr());
    }

    public void updateKey(KeyEntity key) throws UpdateException {
        dao.updateKey(key);
    }
}
