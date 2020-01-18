package com.autopark.app.misc;

public class AdminQueryOutputHelp {
    private String driverName;
    private String routeName;
    private String busName;
    private String accepted;

    public AdminQueryOutputHelp(String driverName, String routeName, String busName, String accepted) {
        this.driverName = driverName;
        this.routeName = routeName;
        this.busName = busName;
        this.accepted = accepted;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }
}
