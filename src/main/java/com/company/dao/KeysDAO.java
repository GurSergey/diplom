package com.company.dao;

import com.company.enitities.DatasetEntity;
import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface KeysDAO {
    public KeyEntity[] getAllKeys() throws SelectException;
    public void saveKey(KeyEntity key) throws InsertException;
    public void updateKey(KeyEntity key) throws UpdateException;
    public void deleteKey(KeyEntity poll) throws DeleteException;
}
