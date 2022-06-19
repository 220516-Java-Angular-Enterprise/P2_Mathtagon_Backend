package com.revature.mathtagon.scores;

import com.revature.mathtagon.user.Users;


import javax.persistence.*;

@Entity

@Table(name = "score")


public class Scores {
    @Id
     private String userID;

    @Column
    private Integer totalScore;
    @Column
    private Integer bestScore;
    @Column
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private Scores score;
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
