package com.example.bmrd.stesbuddy;

/**
 * Created by Dell on 16-Mar-19.
 */

public class NGO {

    public String lon;
    public String name;
    public String location;
    public String domain;
    public String lat;
    public String id;
    public String date;
    public String food;
    public String vacancy;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public NGO(){

    }


    public NGO(String name, String location, String domain, String date, String vacancy,String lat, String lon,String id, String food) {
        this.name = name;
        this.location = location;
        this.domain = domain;
        this.lat=lat;
        this.lon=lon;
        this.id=id;
        this.vacancy=vacancy;
        this.date=date;
        this.food=food;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
