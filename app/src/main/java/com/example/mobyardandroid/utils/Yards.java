package com.example.mobyardandroid.utils;

public class Yards {

    boolean exist;
    String name, desc, id;
    Double longitude, latitude;
    String owner;
    String[] users, admins;

    public Yards(String id, String name, String desc, Double longitude, Double latitude) {
        this.name = name;
        this.desc = desc;
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.exist = true;
    }

    public Yards() {
        this.name = "Yard";
        this.desc = "";
        this.id = "";
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.exist = false;
    }

    public boolean isExist() {
        return exist;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getId() {
        return id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getOwner() {
        return owner;
    }

    public String[] getUsers() {
        return users;
    }

    public String[] getAdmins() {
        return admins;
    }


}
