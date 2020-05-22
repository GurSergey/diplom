package com.company.enitities;

import java.sql.Date;
import java.sql.Timestamp;

public class UserEntity implements Entity {
    public UserEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UserEntity(int id, String login, Timestamp registrationDate, String password) {
        this.id = id;
        this.login = login;
        this.registrationDate = registrationDate;
        this.password = password;
    }

    int id;
    Timestamp registrationDate;

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
