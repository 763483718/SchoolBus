package com.example.answer.dto;

import lombok.ToString;

@ToString
public class RouteStopDTO {
    private int routeID;
    private String routeName;
    private String stopList;
    public RouteStopDTO(){
    }
    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStopList() {
        return stopList;
    }

    public void setStopList(String stopList) {
        this.stopList = stopList;
    }
}
