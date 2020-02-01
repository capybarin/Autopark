package com.autopark.app.entities;

/**
 * Модель для таблицы Route
 * @author Bezdushnyi Vladyslav
 */

public class Route {
    private int id;
    private String name;

    public Route(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Route(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
