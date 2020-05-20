package com.company.services;

import com.company.dao.DatasetDAO;
import com.company.enitities.DatasetEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

import java.io.*;
import java.security.SecureRandom;


public class DataSetService {
    private DatasetDAO dao;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static final int SIZE_FILE_ID = 50;
    private static String generateFilename() {
//        if (SIZE_SESSION_ID < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(SIZE_FILE_ID);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < SIZE_FILE_ID; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);
            sb.append(rndChar);

        }

        return sb.toString();

    }



    public DataSetService(DatasetDAO datasetDAO) {
        this.dao = datasetDAO;
    }

    public DatasetEntity[] getAllDataSet() throws SelectException {
        return dao.getAllDataSet();
    }

    public DatasetEntity getById(int id) throws SelectException {
        return dao.getById(id);
    }

    public void saveDataset(DatasetEntity dataset, byte[] file) throws InsertException, IOException {
        dataset.setFilename(generateFilename()+".csv");
        String filename = "datasets"+ File.separator+dataset.getFilename();
        File targetFile = new File(filename);
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(file);
        this.dao.saveDataset(dataset);
    }

    public void updateDataset(DatasetEntity dataset) throws UpdateException {
        this.dao.updateDataset(dataset);
    }

    public void deleteDataset(DatasetEntity dataset) throws DeleteException, SelectException {
        dataset = this.getById(dataset.getId());
        File file = new File("datasets"+ File.separator+dataset.getFilename());
        if(!file.delete()) {
            throw new DeleteException();
        }
        this.dao.deleteDataset(dataset);
    }

    public void downloadDataset(DatasetEntity datasetEntity){

    }

}
