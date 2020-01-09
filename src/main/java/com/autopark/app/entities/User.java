package com.autopark.app.entities;

import java.util.Objects;

public class User {
    private String name;
    private String password;
    private String surname;
    private String role;
    private String activity;

    public User(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = "U";
        this.activity = "free";
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public String getActivity() {
        return activity;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
