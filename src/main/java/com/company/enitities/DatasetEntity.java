package com.company.enitities;

import java.sql.Date;
import java.util.ArrayList;

public class DatasetEntity implements Entity {
    public int getId() {
        return id;
    }

    public DatasetEntity(){}

    public DatasetEntity(int id, String title, String filename, Date createDate) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.createdDate = createDate;
    }

    int id;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    String title;
    String filename;
    Date createdDate;



}
