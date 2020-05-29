package com.company.enitities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DatasetEntity implements Entity {


    public DatasetEntity(){}

    public DatasetEntity(int id, String title, String filename, boolean checking,
                         boolean isCorrect, Timestamp createDate) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.createdDate = createDate;
        this.checking = checking;
        this.isCorrect =  isCorrect;
    }

    int id;
    boolean checking;
    boolean isCorrect;
    String title;
    String filename;
    Timestamp createdDate;
    public int getId() {
        return id;
    }

    public boolean getChecking() {
        return checking;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }





}
