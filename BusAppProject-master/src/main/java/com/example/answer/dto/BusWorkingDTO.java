package com.example.answer.dto;

import lombok.ToString;

@ToString
public class BusWorkingDTO {
    int busID;
    int routeID;
    int driverID;
    String driverName;
    String licence;
    String routeName;
    String startStop;
    String endStop;
    String distance;
    String forecastTime;
    int direction;
    String lat;
    String lng;

    public BusWorkingDTO() {
    }

//    public BusWorkingDTO(int busCode, int routeCode, int driverCode, String driverName, String licence, String routeName, String startStop, String endStop, int direction, String lat, String lng) {
//        this.busCode = busCode;
//        this.routeCode = routeCode;
//        this.driverCode = driverCode;
//        this.driverName = driverName;
//        this.licence = licence;
//        this.routeName = routeName;
//        this.startStop = startStop;
//        this.endStop = endStop;
//        this.direction = direction;
//        this.lat = lat;
//        this.lng = lng;
//    }


    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
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

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartStop() {
        return startStop;
    }

    public void setStartStop(String startStop) {
        this.startStop = startStop;
    }

    public String getEndStop() {
        return endStop;
    }

    public void setEndStop(String endStop) {
        this.endStop = endStop;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(String forecastTime) {
        this.forecastTime = forecastTime;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
