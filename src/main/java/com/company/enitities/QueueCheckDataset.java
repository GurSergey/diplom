package com.company.enitities;

import java.sql.Timestamp;

public class QueueCheckDataset {

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(boolean completedTask) {
        this.completedTask = completedTask;
    }

    public boolean isNormalize() {
        return normalize;
    }

    public void setNormalize(boolean normalize) {
        this.normalize = normalize;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public DatasetEntity getDataset() {
        return dataset;
    }

    public void setDataset(DatasetEntity dataset) {
        this.dataset = dataset;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public QueueCheckDataset() {
    }

    public QueueCheckDataset(int id, boolean completedTask, boolean normalize, boolean inWork, boolean isCorrect, DatasetEntity dataset, Timestamp createdDate) {
        this.id = id;
        this.completedTask = completedTask;
        this.normalize = normalize;
        this.inWork = inWork;
        this.isCorrect = isCorrect;
        this.dataset = dataset;
        this.createdDate = createdDate;
    }

    boolean completedTask ;
    boolean normalize ;
    boolean inWork;
    boolean isCorrect;
    DatasetEntity dataset;
    Timestamp createdDate;

}
