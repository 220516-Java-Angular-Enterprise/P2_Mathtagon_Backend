package com.revature.mathtagon.user;

import com.revature.mathtagon.mathbattle.Scores;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")

public class User {

    @Id
    private String userID;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String fullName;
    @Column
    private Integer age;

    @OneToMany(mappedBy = "users")
    private List<Scores> scores;


    public User(){}



    public User(String userID, String username, String password, String email, String fullName, Integer age) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.age = age;

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +

                '}';
    }
}
