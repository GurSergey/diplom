package com.company.dao;

import com.company.enitities.DatasetEntity;
import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface DatasetDAO {
    DatasetEntity[] getAllDataSet() throws SelectException;
    DatasetEntity getById(int id) throws SelectException;
    void saveDataset(DatasetEntity key, boolean normalize) throws InsertException;
    void updateDataset(DatasetEntity key) throws UpdateException;
    void deleteDataset(DatasetEntity poll) throws DeleteException;
    void mergeDataset(DatasetEntity dataset, int[] ids) throws InsertException;
}
