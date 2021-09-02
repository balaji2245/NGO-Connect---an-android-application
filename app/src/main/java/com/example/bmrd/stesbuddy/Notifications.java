package com.example.bmrd.stesbuddy;

/**
 * Created by Dell on 26-Aug-18.
 */

public class Notifications {


    public String info;
    public String time;

    public Notifications(){

    }

    public Notifications(String info, String time) {
        this.info = info;
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
