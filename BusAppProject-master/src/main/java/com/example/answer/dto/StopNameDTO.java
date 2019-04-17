package com.example.answer.dto;

import lombok.ToString;

@ToString
public class StopNameDTO implements Comparable<StopNameDTO>{
    private String stopID;
    private String stopName;
    private String distance;
    private String lng;
    private String lat;

    public StopNameDTO() {
    }

    public StopNameDTO(String stopID, String stopName, String lng, String lat) {
        this.stopID = stopID;
        this.stopName = stopName;
        this.lng = lng;
        this.lat = lat;
    }
    public String getStopID() {
        return stopID;
    }
    public void setStopID(String stopID) {
        this.stopID = stopID;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    @Override
    public int compareTo(StopNameDTO o) {
        double i = Double.parseDouble(this.getDistance()) - Double.parseDouble(o.getDistance());//根据距离排序
        return (int) i;
    }

}
