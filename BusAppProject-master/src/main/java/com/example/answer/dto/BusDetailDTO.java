package com.example.answer.dto;

import lombok.ToString;

@ToString
public class BusDetailDTO {
    String startStop;
    String endStop;
    String startTime;
    String endTime;
    String price;
    String stopList;
    int currentStopIndex;
    int stopHoverIndex;

    public BusDetailDTO() {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStopList() {
        return stopList;
    }

    public void setStopList(String stopList) {
        this.stopList = stopList;
    }

    public int getCurrentStopIndex() {
        return currentStopIndex;
    }

    public void setCurrentStopIndex(int currentStopIndex) {
        this.currentStopIndex = currentStopIndex;
    }

    public int getStopHoverIndex() {
        return stopHoverIndex;
    }

    public void setStopHoverIndex(int stopHoverIndex) {
        this.stopHoverIndex = stopHoverIndex;
    }
}
