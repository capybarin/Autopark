package com.autopark.app.model;

import com.autopark.app.entities.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();
    private Connection connection;
    private List<User> model;


    private Model() {
        model = new ArrayList<>();
    }


    
    public static Model getInstance() {
        return instance;
    }

    public void addUser(User user){
        model.add(user);
    }

    public List<String> getNames(){
        return model.stream().map(User::getName).collect(Collectors.toList());
    }

    public List<String> getSurnames(){
        return model.stream().map(User::getSurname).collect(Collectors.toList());
    }

    public List<String> getRoles(){
        return model.stream().map(User::getRole).collect(Collectors.toList());
    }

    public List<String> getActivities(){
        return model.stream().map(User::getActivity).collect(Collectors.toList());
    }
}
