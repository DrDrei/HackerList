package com.andreiusenka.hackerlist.entities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class User {
    private String email;
    private Map<String, String> dateJoined;
    private List<String> taskList;


    // TODO: 2017-01-28 add any other settings that are required here. 

    public User() {}

    public User(String email) {
        this.email = email;
        this.dateJoined = ServerValue.TIMESTAMP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Map<String, String> dateJoined) {
        this.dateJoined = dateJoined;
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }
}
