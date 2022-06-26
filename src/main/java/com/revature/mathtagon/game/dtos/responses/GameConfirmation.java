package com.revature.mathtagon.game.dtos.responses;

import com.revature.mathtagon.game.Game;

public class GameConfirmation {
    private String userID;
    private String gameType;

    public GameConfirmation() {
    }

    public GameConfirmation(Game game) {
        this.userID = game.getUser().getUserID();
        this.gameType = game.getGametype().name();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @Override
    public String toString() {
        return "GameConfirmation{" +
                "userID='" + userID + '\'' +
                ", gameType=" + gameType +
                '}';
    }
}
