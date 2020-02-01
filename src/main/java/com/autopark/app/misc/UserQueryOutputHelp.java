package com.autopark.app.misc;

/**
 * Класс призванный хранить заказы для водителей,
 * но в более удобной форме где вместо ID будут данные им соотвуствующие
 * @author Bezdushnyi Vladyslav
 */

public class UserQueryOutputHelp {
    private int workId;
    private String routeName;
    private String busName;
    private String accepted;

    public UserQueryOutputHelp(int workId, String routeName, String busName, String accepted) {
        this.workId = workId;
        this.routeName = routeName;
        this.busName = busName;
        this.accepted = accepted;
    }

    public int getWorkId() {
        return workId;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getBusName() {
        return busName;
    }

    public String getAccepted() {
        return accepted;
    }
}
