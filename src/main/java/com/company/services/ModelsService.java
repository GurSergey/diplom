package com.company.services;

import com.company.dao.ModelsDAO;
import com.company.enitities.DatasetEntity;
import com.company.enitities.ModelEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

import java.io.File;


public class ModelsService {
    ModelsDAO dao;

    public ModelsService(ModelsDAO dao)
    {
        this.dao = dao;
    }

    public ModelEntity[] getModels() throws SelectException {
        return dao.getAllModels();
    }

    public void updateModel(ModelEntity model) throws UpdateException {
        dao.updateModel(model);
    }

    public void saveModel(ModelEntity model, DatasetEntity dataset) throws InsertException {
        dao.saveModel(model, dataset);
    }

    public void deleteModel(ModelEntity model) throws DeleteException {
        File file = new File("../models"+ File.separator+model.getId()+".sav");
        if(!file.delete()) {
            throw new DeleteException();
        }
        dao.deleteModel(model);
    }

    public ModelEntity[] getAllCompletedModels() throws SelectException{
        return dao.getAllCompletedModels();
    }

//    public DatasetEntity[] getAllOpenPolls() throws SelectException{
//        return repository.getAllOpenPolls();
//    }
//
//    public DatasetEntity[] getAllEndedPolls() throws SelectException{
//        return repository.getAllEndedPolls();
//    }
//
//    public DatasetEntity getPollResult(int id) throws SelectException{
//        return repository.getPollResult(id);
//    }
//
//    public DatasetEntity getPollWithAnswersUser(int pollId, int userId) throws SelectException{
//        return repository.getPollWithAnswersUser(pollId, userId);
//    }
//
//    public DatasetEntity[] getPollsByUser(int userId) throws SelectException {
//        return repository.getPollsByUser(userId);
//    }

}
