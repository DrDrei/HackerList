package com.andreiusenka.hackerlist.entities;

import java.util.HashMap;

/**
 * Created by drei on 2017-01-28.
 */

public class User {
    private String email;
    private HashMap<String, Object> dateJoined;

    public User() {}

    public User(String email, HashMap<String, Object> dateJoined) {
        this.email = email;
        this.dateJoined = dateJoined;
    }


}
