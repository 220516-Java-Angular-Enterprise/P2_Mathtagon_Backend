package com.revature.mathtagon.mathbattle;

import com.revature.mathtagon.user.User;
import org.hibernate.annotations.MapKeyType;


import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column
    private Integer totalScore;
    @Column
    private Integer bestScore;
    @Column
    private Integer level;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;



    public Score(User user, Integer totalScore, Integer bestScore, Integer level) {
        this.user = user;
        this.totalScore = totalScore;
        this.bestScore = bestScore;
        this.level = level;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "user=" + user +
                ", totalScore=" + totalScore +
                ", bestScore=" + bestScore +
                ", level=" + level +
                '}';
    }
}
