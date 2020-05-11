package com.company.enitities;

import java.sql.Date;

public class VoterEntity implements Entity {
    public VoterEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public VoterEntity(int id, String login, Date registrationDate, String name, String phone, String password) {
        this.id = id;
        this.login = login;
        this.registrationDate = registrationDate;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    int id;
    Date registrationDate;
    String name;
    String phone;
    String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;

}
