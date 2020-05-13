package com.company.dao;

import com.company.enitities.DatasetEntity;
import com.company.enitities.ModelEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface ModelsDAO {
    ModelEntity[] getAllModels() throws SelectException;
    void saveModel(ModelEntity model, DatasetEntity dataset) throws InsertException;
    void updateModel(ModelEntity model) throws UpdateException;
    void deleteModel(ModelEntity model) throws DeleteException;
}
