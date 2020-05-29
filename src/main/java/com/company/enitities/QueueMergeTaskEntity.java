package com.company.enitities;

import java.sql.Timestamp;

public class QueueMergeTaskEntity implements Entity {
    int id ;
    boolean completedTask;
    DatasetEntity dataset;
    boolean inWork;
    String sourceDatasets;
    Timestamp createdDate;

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

    public DatasetEntity getDataset() {
        return dataset;
    }

    public QueueMergeTaskEntity() {

    }

    public QueueMergeTaskEntity(int id, boolean completedTask, DatasetEntity dataset, boolean inWork, String sourceDatasets, Timestamp createdDate) {
        this.id = id;
        this.completedTask = completedTask;
        this.dataset = dataset;
        this.inWork = inWork;
        this.sourceDatasets = sourceDatasets;
        this.createdDate = createdDate;
    }

    public void setDataset(DatasetEntity dataset) {
        this.dataset = dataset;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    public String getSourceDatasets() {
        return sourceDatasets;
    }

    public void setSourceDatasets(String sourceDatasets) {
        this.sourceDatasets = sourceDatasets;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
