package com.company.enitities;

import java.sql.Timestamp;

public class QueueTaskUserEntity {
    int id;
    boolean completedTask;
    int progress;
    boolean inWork;
    ModelEntity model;
    Timestamp created_date;
    UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public QueueTaskUserEntity() {
    }

    public QueueTaskUserEntity(int id, boolean completedTask, int progress, boolean inWork, ModelEntity model,
                               Timestamp created_date, UserEntity user) {
        this.id = id;
        this.completedTask = completedTask;
        this.progress = progress;
        this.inWork = inWork;
        this.model = model;
        this.created_date = created_date;
        this.user = user;
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
