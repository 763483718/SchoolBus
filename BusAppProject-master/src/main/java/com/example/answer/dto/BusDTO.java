package com.example.answer.dto;

import lombok.ToString;

@ToString
public class BusDTO {
    String lng;
    String lat;
    int currentStopIndex;
    String currentStopTime;
    String currentPositionTime;

    public BusDTO() {
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getCurrentStopIndex() {
        return currentStopIndex;
    }

    public void setCurrentStopIndex(int currentStopIndex) {
        this.currentStopIndex = currentStopIndex;
    }

    public String getCurrentStopTime() {
        return currentStopTime;
    }

    public void setCurrentStopTime(String currentStopTime) {
        this.currentStopTime = currentStopTime;
    }

    public String getCurrentPositionTime() {
        return currentPositionTime;
    }

    public void setCurrentPositionTime(String currentPositionTime) {
        this.currentPositionTime = currentPositionTime;
    }
}
