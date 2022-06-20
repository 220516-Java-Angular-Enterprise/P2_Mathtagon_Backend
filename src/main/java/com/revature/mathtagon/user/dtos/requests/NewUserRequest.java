package com.revature.mathtagon.user.dtos.requests;

import com.revature.mathtagon.auth.AuthController;
import com.revature.mathtagon.user.User;

import org.apache.tomcat.util.json.JSONParser;

import java.util.logging.Logger;

public class NewUserRequest {
    private static final Logger logger = Logger.getLogger(NewUserRequest.class.getName());



    private String username;
    private String password;
    private String fullName;
    private String email;
    private int age;


    public NewUserRequest(){super();}

    public NewUserRequest(String username, String password, String fullName, String email, Integer age) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public User takeUser(){
        return new User(username,password,email,fullName,age);
    }


    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age +
                '}';
    }
}
