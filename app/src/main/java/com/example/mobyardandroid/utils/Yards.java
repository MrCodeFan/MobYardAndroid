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
        this.name = "";
        this.desc = "";
        this.id = "";
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.exist = false;
    }
}
