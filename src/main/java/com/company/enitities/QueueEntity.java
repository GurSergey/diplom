package com.company.enitities;

import java.sql.Date;

public class QueueEntity {
    private int id;
    private boolean completedLearn;
    ModelEntity model;
    Date createdDate;

    public int getProgress() {
        return progress;
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
        return completedLearn;
    }

    public void setCompletedLearn(boolean completedLearn) {
        this.completedLearn = completedLearn;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public QueueEntity(int id, boolean completedLearn, ModelEntity model, Date createdDate) {
        this.id = id;
        this.completedLearn = completedLearn;
        this.model = model;
        this.createdDate = createdDate;
    }
}
