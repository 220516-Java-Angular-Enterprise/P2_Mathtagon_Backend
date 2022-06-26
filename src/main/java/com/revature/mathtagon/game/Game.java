package com.revature.mathtagon.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.mathtagon.user.User;


import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {


    public enum GameType{
        ALGEBRA
    }
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userID")
    private User user;

    @Column
    private GameType gametype;
    @Column
    private Integer score;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameID;

    public Game(){
        super();
    }

    //Create Constructor
    public Game(User user, int gameTypeID) {
        this.user = user;
        this.gametype = GameType.values()[gameTypeID];
    }

    //Save Constructor
    public Game(User user, int gametype, Integer score) {
        this.user = user;
        this.gametype = GameType.values()[gametype];
        this.score = score;
    }


    //Read Constructor

    public Game(User user, GameType gameType, Integer score, Integer id) {
        this.user = user;
        this.gametype = gameType;
        this.score = score;
        this.gameID = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameType getGametype() {
        return gametype;
    }

    public void setGametype(GameType gametype) {
        this.gametype = gametype;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    @Override
    public String toString() {
        return "Game{" +
                "user=" + user.getUserID() +
                ", gameType=" + gametype +
                ", score=" + score +
                ", id=" + gameID +
                '}';
    }
}
