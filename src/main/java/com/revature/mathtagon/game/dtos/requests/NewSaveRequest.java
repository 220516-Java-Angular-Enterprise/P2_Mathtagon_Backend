package com.revature.mathtagon.game.dtos.requests;

public class NewSaveRequest {
    private String userID;

    private int gametype;

    private int score;

    public NewSaveRequest(String userID, int gametype, int score) {
        this.userID = userID;
        this.gametype = gametype;
        this.score = score;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getGametype() {
        return gametype;
    }

    public void setGametype(int gametype) {
        this.gametype = gametype;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "NewSaveRequest{" +
                "user=" + userID +
                ", gametype=" + gametype +
                ", score=" + score +
                '}';
    }
}
