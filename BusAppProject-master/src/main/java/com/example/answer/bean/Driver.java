package com.example.answer.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Driver {
    private int dirverID;
    private String driverTelephone;
    private String dirverAccount;
    private String dirverPassword;
    private int busID;
    private String driverName;
    private String licence;

    public Driver(){

    }
    public int getDirverID() {
        return dirverID;
    }

    public void setDirverID(int dirverID) {
        this.dirverID = dirverID;
    }

    public String getDriverTelephone() {
        return driverTelephone;
    }

    public void setDriverTelephone(String driverTelephone) {
        this.driverTelephone = driverTelephone;
    }

    public String getDirverAccount() {
        return dirverAccount;
    }

    public void setDirverAccount(String dirverAccount) {
        this.dirverAccount = dirverAccount;
    }

    public String getDirverPassword() {
        return dirverPassword;
    }

    public void setDirverPassword(String dirverPassword) {
        this.dirverPassword = dirverPassword;
    }

    public int getBusID() { return busID; }

    public void setBusID(int busID) { this.busID = busID; }

    public String getDriverName() { return driverName; }

    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getLicence() { return licence; }

    public void setLicence(String licence) { this.licence = licence; }
}
