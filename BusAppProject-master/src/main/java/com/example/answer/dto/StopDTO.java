package com.example.answer.dto;

import lombok.ToString;

@ToString
public class StopDTO {
    int stopID;
    String lng;
    String lat;
    public StopDTO (){

    }
    public int getStopID() {
        return stopID;
    }

    public void setStopID(int stopID) {
        this.stopID = stopID;
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

}
