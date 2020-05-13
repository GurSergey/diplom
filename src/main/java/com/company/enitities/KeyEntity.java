package com.company.enitities;

import java.sql.Date;

public class KeyEntity implements Entity {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public KeyEntity(){};

    public KeyEntity(int id, String name, String keyStr, Date createdDate) {
        this.id = id;
        this.name = name;
        this.keyStr = keyStr;
        this.createdDate = createdDate;
    }

    private int id;
    private String name;

    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }

    private String keyStr;
    private Date createdDate;
}
