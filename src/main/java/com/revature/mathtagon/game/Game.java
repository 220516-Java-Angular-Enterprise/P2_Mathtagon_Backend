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
    public GameType gametype;
    @Column
    private Integer score;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameID;

    public Game(){
        super();
    }

    public Game(Integer score, User user){
        this.score = score;
        this.user = user;

    }

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
                "user=" + user +
                ", gameType=" + gametype +
                ", score=" + score +
                ", id=" + gameID +
                '}';
    }
}
