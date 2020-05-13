package com.company.dao;

import com.company.enitities.DatasetEntity;
import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface DatasetDAO {
    DatasetEntity[] getAllDataSet() throws SelectException;
    void saveDataset(DatasetEntity key) throws InsertException;
    void updateDataset(DatasetEntity key) throws UpdateException;
    void deleteDataset(DatasetEntity poll) throws DeleteException;
}
