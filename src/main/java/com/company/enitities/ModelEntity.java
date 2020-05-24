package com.company.enitities;

import java.sql.Date;
import java.sql.Timestamp;

public class ModelEntity implements Entity {
    private int id;
    public ModelEntity(){}
    public ModelEntity(int id, String title, boolean completedLearn,
                       String datasetName, Timestamp createDate, int progress, double testAccuracy) {
        this.id = id;
        this.title = title;
        this.completedLearn = completedLearn;
        this.datasetName = datasetName;
        this.createDate = createDate;
        this.progress = progress;
        this.testAccuracy = testAccuracy;
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

//    public DatasetEntity getDataset() {
//        return dataset;
//    }
//
//    public void setDataset(DatasetEntity dataset) {
//        this.dataset = dataset;
//    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }
    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    private int progress;
    private String title;
    private boolean completedLearn;

    public double getTestAccuracy() {
        return testAccuracy;
    }

    public void setTestAccuracy(double testAccuracy) {
        this.testAccuracy = testAccuracy;
    }

    private double testAccuracy;


    private String datasetName;
    private Timestamp createDate;
}
