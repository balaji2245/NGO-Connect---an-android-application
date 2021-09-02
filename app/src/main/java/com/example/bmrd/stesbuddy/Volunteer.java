package com.example.bmrd.stesbuddy;

/**
 * Created by Dell on 22-Mar-19.
 */

public class Volunteer {
    public String id;
    public String name;


    public String contact;
    public String age;
    public String gender;
    public String photo;
    public String qual;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public Volunteer(){

    }

    public Volunteer(String id, String name, String age, String contact, String gender, String qual, String photo) {
        this.id = id;
        this.name = name;

        this.age= age;
        this.contact= contact;
        this.gender= gender;
        this.qual=  qual;
        this.photo= photo;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }






}
