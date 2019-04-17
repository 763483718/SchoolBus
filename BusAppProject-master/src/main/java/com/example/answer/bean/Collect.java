package com.example.answer.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Collect {
    private int collectID;
    private int userID;
    private int routeID;
    private int direction;

    public Collect() {
    }

    public Collect(int routeID, int direction) {
        this.routeID = routeID;
        this.direction = direction;
    }

    public int getCollectID() {
        return collectID;
    }

    public void setCollectID(int collectID) {
        this.collectID = collectID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
