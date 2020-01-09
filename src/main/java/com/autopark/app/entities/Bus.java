package com.autopark.app.entities;

public class Bus {
    private int id;
    private String name;
    private String activity;

    public Bus(int id, String name, String activity) {
        this.id = id;
        this.name = name;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActivity() {
        return activity;
    }
}
