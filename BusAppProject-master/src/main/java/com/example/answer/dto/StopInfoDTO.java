package com.example.answer.dto;

import lombok.ToString;

import java.util.List;

@ToString
public class StopInfoDTO {
    private String stopName;
    private String lng;
    private String lat;
    private String relateRouteID;
    private String distance;
    private List<RouteNameDTO> routeNameList;
    public StopInfoDTO(String stopName, String lng, String lat, String relateRouteID) {
        this.stopName = stopName;
        this.lng = lng;
        this.lat = lat;
        this.relateRouteID = relateRouteID;
    }
    public StopInfoDTO(){
    }
    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
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

    public String getRelateRouteID() {
        return relateRouteID;
    }

    public void setRelateRouteID(String relateRouteID) {
        this.relateRouteID = relateRouteID;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<RouteNameDTO> getRouteNameList() {
        return routeNameList;
    }

    public void setRouteNameList(List<RouteNameDTO> routeNameList) {
        this.routeNameList = routeNameList;
    }



}
