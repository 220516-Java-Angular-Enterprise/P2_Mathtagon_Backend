package com.revature.mathtagon.game.dtos.responses;

import com.revature.mathtagon.game.Game;

public class GameConfirmation {
    private String userID;
    private String gametype;

    public GameConfirmation() {}

    public GameConfirmation(Game game) {
        this.userID = game.getUser().getUserID();
        this.gametype = game.getGametype().name();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype;
    }

    @Override
    public String toString() {
        return "GameConfirmation{" +
                "userID='" + userID + '\'' +
                ", gametype=" + gametype +
                '}';
    }
}
