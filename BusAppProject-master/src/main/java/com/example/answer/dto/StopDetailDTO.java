package com.example.answer.dto;

import lombok.ToString;

@ToString
public class StopDetailDTO {
    String estimatedTime;
    String distance;
    int offsetStop;
    String stopName;
    String stopStatus;

    public StopDetailDTO() {
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getOffsetStop() {
        return offsetStop;
    }

    public void setOffsetStop(int offsetStop) {
        this.offsetStop = offsetStop;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(String stopStatus) {
        this.stopStatus = stopStatus;
    }
}
