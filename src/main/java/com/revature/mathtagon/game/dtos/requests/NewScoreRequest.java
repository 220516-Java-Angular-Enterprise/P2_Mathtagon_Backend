package com.revature.mathtagon.game.dtos.requests;

import com.revature.mathtagon.user.User;

public class NewScoreRequest {
    public enum GameType{ALGEBRA}
    private String userID;

    private GameType gametype;

    private int score;

    public NewScoreRequest(String userID, GameType gameType, int score) {
        this.userID = userID;
        this.gametype = gameType;
        this.score = score;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User takeUser(){
        return new User(userID);
    }

    public GameType getGametype() {
        return gametype;
    }

    public void setGametype(GameType gametype) {
        this.gametype = gametype;
    }

    @Override
    public String toString() {
        return "NewScoreRequest{" +
                "userID='" + userID + '\'' +
                ", gameType=" + gametype +
                ", score=" + score +
                '}';
    }
}
