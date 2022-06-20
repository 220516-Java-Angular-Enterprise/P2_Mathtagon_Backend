package com.revature.mathtagon.mathbattle;

import com.revature.mathtagon.user.User;


import javax.persistence.*;

@Entity

@Table(name = "score")


public class Score {
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
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private Score score;
    public Score(String userID, Integer totalScore, Integer bestScore, Integer level) {
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
        return "Score{" +
                "userID='" + userID + '\'' +
                ", totalScore=" + totalScore +
                ", bestScore=" + bestScore +
                ", level=" + level +
                '}';
    }
}
