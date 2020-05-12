package com.company.enitities;

import java.sql.Date;

public class ModelEntity implements Entity {
    private int id;

    public ModelEntity(int id, String title, boolean completedLearn, DatasetEntity dataset, Date createDate) {
        this.id = id;
        this.title = title;
        this.completedLearn = completedLearn;
        this.dataset = dataset;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompletedLearn() {
        return completedLearn;
    }

    public void setCompletedLearn(boolean completedLearn) {
        this.completedLearn = completedLearn;
    }

    public DatasetEntity getDataset() {
        return dataset;
    }

    public void setDataset(DatasetEntity dataset) {
        this.dataset = dataset;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    private int progress;
    private String title;
    private boolean completedLearn;
    private DatasetEntity dataset;
    private Date createDate;
}
