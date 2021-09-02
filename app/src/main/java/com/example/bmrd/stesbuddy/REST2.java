package com.example.bmrd.stesbuddy;

/**
 * Created by Dell on 23-Mar-19.
 */

public class REST2 {


    public String lon;
    public String name;
    public String location;
    public String lat;
    public String id;
    public String food;
    public String fssai;
    public String contact;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFssai() {
        return fssai;
    }

    public void setFssai(String fssai) {
        this.fssai = fssai;
    }

    public REST2(){

    }

    public REST2(String lon, String name, String location, String lat, String id, String food, String fssai, String contact, String status) {
        this.lon = lon;
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.id = id;
        this.food = food;
        this.fssai = fssai;
        this.contact =contact;
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
