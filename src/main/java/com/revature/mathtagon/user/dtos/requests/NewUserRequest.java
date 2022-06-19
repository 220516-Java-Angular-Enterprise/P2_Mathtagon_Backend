package com.revature.mathtagon.user.dtos.requests;

import com.revature.mathtagon.user.Users;

public class NewUserRequest {
    private String username;
    private String password;

    public NewUserRequest(){super();}
    public NewUserRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users takeUser(){
        return new Users(username, password);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
