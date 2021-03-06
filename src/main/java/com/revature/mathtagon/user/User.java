package com.revature.mathtagon.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.mathtagon.game.Game;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String fullname;
    @Column
    private Integer age;



    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Game> games;

    public User(){
        games = new ArrayList<>();
    }



    public User(String userID, String username, String password, String email, String fullName, Integer age, List<Game> games) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullName;
        this.age = age;
        this.games = games;

    }
    public User( String username, String password,String email, String fullname,Integer age){
        //userID = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.age = age;

    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String userID) {
        this.userID = userID;
    }


    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
                ", fullname='" + fullname + '\'' +
                ", age=" + age +
                ", games=" + games +
                '}';
    }
}
