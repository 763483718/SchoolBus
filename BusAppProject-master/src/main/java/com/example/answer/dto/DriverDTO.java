package com.example.answer.dto;

import lombok.ToString;

@ToString
public class DriverDTO {
    private int dirverID;
    private int busID;
    private String driverTelephone;
    private String driverName;
    private String licence;
    private int isDepart;
    private int routeID;
    private int currentStopIndex;
    private String IsDepartName;

    public DriverDTO() {
    }

    public int getDirverID() { return dirverID; }

    public void setDirverID(int dirverID) {
        this.dirverID = dirverID;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public String getDriverTelephone() {
        return driverTelephone;
    }

    public void setDriverTelephone(String driverTelephone) {
        this.driverTelephone = driverTelephone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public int getIsDepart() { return isDepart; }

    public void setIsDepart(int isDepart) { this.isDepart = isDepart; }

    public int getRouteID() { return routeID; }

    public void setRouteID(int routeID) { this.routeID = routeID; }

    public int getCurrentStopIndex() { return currentStopIndex; }

    public void setCurrentStopIndex(int currentStopIndex) { this.currentStopIndex = currentStopIndex; }

    public String getIsDepartName() {
        return IsDepartName;
    }

    public void setIsDepartName(String isDepartName) {
        IsDepartName = isDepartName;
    }

}
