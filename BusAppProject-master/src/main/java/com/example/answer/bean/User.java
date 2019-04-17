package com.example.answer.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private int userID;
    private String userAccount;
    private String userPassword;
    private String userTelephone;
    private String userEmail;
    private String userName;
    private String userPosition;
    private String collectRouteID;
    public User(){

    }

    public void setCollectRouteID(String collectRouteID) {
        this.collectRouteID = collectRouteID;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCollectRouteID() {
        return collectRouteID;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
