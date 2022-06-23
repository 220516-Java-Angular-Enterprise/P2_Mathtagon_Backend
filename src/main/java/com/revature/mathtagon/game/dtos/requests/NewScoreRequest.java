package com.revature.mathtagon.game.dtos.requests;

import com.revature.mathtagon.user.User;

public class NewScoreRequest {
    private String userID;
    private String password;

    private int score;

    public NewScoreRequest(String username, String password, int score) {
        this.userID = username;
        this.password = password;
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


    @Override
    public String toString() {
        return "NewScoreRequest{" +
                "userID='" + userID + '\'' +
                ", score=" + score +
                '}';
    }
}
