package com.autopark.app.entities;

public class Route {
    private int id;
    private String name;

    public Route(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
