package com.revature.mathtagon.auth.dtos.responses;

import com.revature.mathtagon.user.User;

public class Principal {
    private String id;
    private String username;

    public Principal() {}

    //For token verification
    public Principal(String id, String username) {
        this.id = id;
        this.username = username;
    }

    //For token creation
    public Principal(User login) {
        id = login.getUserID();
        username = login.getUsername();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
