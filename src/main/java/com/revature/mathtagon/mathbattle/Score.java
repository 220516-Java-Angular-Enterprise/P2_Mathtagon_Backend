package com.revature.mathtagon.mathbattle;

import com.revature.mathtagon.user.User;


import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column
    private Integer totalscore;
    @Column
    private Integer bestscore;
    @Column
    private Integer level;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;



    public Score(User user, Integer totalScore, Integer bestScore, Integer level) {
        this.user = user;
        this.totalscore = totalScore;
        this.bestscore = bestScore;
        this.level = level;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public Integer getBestscore() {
        return bestscore;
    }

    public void setBestscore(Integer bestscore) {
        this.bestscore = bestscore;
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
                ", totalScore=" + totalscore +
                ", bestScore=" + bestscore +
                ", level=" + level +
                '}';
    }
}
