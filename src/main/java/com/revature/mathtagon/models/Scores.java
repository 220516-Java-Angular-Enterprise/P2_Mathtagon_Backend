package com.revature.mathtagon.models;

public class Scores {
    private String userID;
    private Integer totalScore;
    private Integer bestScore;
    private Integer level;

    public Scores(String userID, Integer totalScore, Integer bestScore, Integer level) {
        this.userID = userID;
        this.totalScore = totalScore;
        this.bestScore = bestScore;
        this.level = level;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getBestScore() {
        return bestScore;
    }

    public void setBestScore(Integer bestScore) {
        this.bestScore = bestScore;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "userID='" + userID + '\'' +
                ", totalScore=" + totalScore +
                ", bestScore=" + bestScore +
                ", level=" + level +
                '}';
    }
}
