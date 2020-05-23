package com.company.enitities;

import java.sql.Timestamp;

public class QueueTaskAdminEntity {
    int id;
    boolean completedTask;
    int progress;
    boolean inWork;
    ModelEntity model;
    Timestamp created_date;
    String title;

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
        this.created_date = created_date;
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

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }
}
