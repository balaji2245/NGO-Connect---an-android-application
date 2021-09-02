package com.example.bmrd.stesbuddy;

/**
 * Created by Dell on 23-Mar-19.
 */

public class REST {


    public String lon;
    public String name;
    public String location;
    public String lat;
    public String id;
    public String food;

    public REST(){

    }

    public REST(String lon, String name, String location, String lat, String id, String food) {
        this.lon = lon;
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.id = id;
        this.food = food;
    }

    public String getLon() {

        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}
