package com.company.enitities;

import java.sql.Date;
import java.sql.Timestamp;

public class QueueTaskMlEntity {
    private int id;
    private boolean completedTask;

    public boolean isCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(boolean completedTask) {
        this.completedTask = completedTask;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    private boolean inWork;
    private ModelEntity model;
    private Timestamp createdDate;
    private int nWorker;

    public int getProgress() {
        return progress;
    }

    public int getnWorker() {
        return nWorker;
    }

    public void setnWorker(int nWorker) {
        this.nWorker = nWorker;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    private int progress;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompletedLearn() {
        return completedTask;
    }

    public void setCompletedLearn(boolean completedLearn) {
        this.completedTask = completedLearn;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public QueueTaskMlEntity(){}

    public QueueTaskMlEntity(int id, boolean completedLearn, ModelEntity model,
                             Timestamp createdDate, boolean inWork, int nWorker, int progress ) {
        this.id = id;
        this.completedTask = completedLearn;
        this.model = model;
        this.createdDate = createdDate;
        this.inWork = inWork;
        this.nWorker = nWorker;
        this.progress = progress;
    }
}
