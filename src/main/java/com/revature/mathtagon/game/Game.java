package com.revature.mathtagon.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.mathtagon.user.User;


import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {


    private enum GameType{
        ALGEBRA
    }
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userID")
    private User user;

    @Column
    private GameType gameType;
    @Column
    private Integer score;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        this.gameType = gameType;
        this.score = score;
        this.gameID = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
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
                ", gameType=" + gameType +
                ", score=" + score +
                ", id=" + gameID +
                '}';
    }
}
