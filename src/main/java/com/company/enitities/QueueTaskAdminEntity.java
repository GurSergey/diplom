package com.company.enitities;

import java.sql.Timestamp;

public class QueueTaskAdminEntity {
    int id;
    boolean completedTask;
    int progress;
    boolean inWork;
    ModelEntity model;
    Timestamp createdDate;
    String title;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    String filename;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QueueTaskAdminEntity() {
    }

    public QueueTaskAdminEntity(int id, String title ,boolean completedTask, int progress, boolean inWork, ModelEntity model, Timestamp created_date) {
        this.id = id;
        this.title = title;
        this.completedTask = completedTask;
        this.progress = progress;
        this.inWork = inWork;
        this.model = model;
        this.createdDate = created_date;
    }

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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
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

    public void setCreated_date(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
