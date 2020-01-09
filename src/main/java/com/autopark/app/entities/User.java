package com.autopark.app.entities;


public class User {
    private int id;
    private String name;
    private String password;
    private String surname;
    private String role;
    private String activity;

    public User(String name, String surname, String role, String activity, String password) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.activity = activity;
        this.password = password;
    }

    public User(int id, String name, String surname, String role, String activity, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.activity = activity;
        this.password = password;
    }

    public int getId() { return id; }

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
