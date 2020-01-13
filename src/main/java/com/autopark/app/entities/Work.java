package com.autopark.app.entities;

public class Work {
    private int id;
    private int userId;
    private int routeId;
    private int busId;
    private String accepted;

    public Work(int id, int userId, int routeId, int busId, String accepted) {
        this.id = id;
        this.userId = userId;
        this.routeId = routeId;
        this.busId = busId;
        this.accepted = accepted;
    }

    public Work(int userId, int routeId, int busId, String accepted) {
        this.userId = userId;
        this.routeId = routeId;
        this.busId = busId;
        this.accepted = accepted;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getBusId() {
        return busId;
    }

    public String getAccepted() {
        return accepted;
    }
}
