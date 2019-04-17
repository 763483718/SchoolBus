package com.example.answer.dto;

import lombok.ToString;

@ToString
public class RouteNameDTO {
    private int RouteID;
    private String RouteName;

    public RouteNameDTO(){}

    public int getRouteID() {
        return RouteID;
    }

    public void setRouteID(int routeID) {
        RouteID = routeID;
    }

    public String getRouteName() {
        return RouteName;
    }

    public void setRouteName(String routeName) {
        RouteName = routeName;
    }

}
