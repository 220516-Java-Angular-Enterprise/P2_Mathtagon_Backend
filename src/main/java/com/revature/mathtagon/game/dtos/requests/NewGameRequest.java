package com.revature.mathtagon.game.dtos.requests;

import com.revature.mathtagon.game.Game;

public class NewGameRequest {
    public enum GameType{
        ALGEBRA
    }
    private GameType gametype;

    NewGameRequest(){super();}

    NewGameRequest(GameType gametype){
        this.gametype = gametype;
    }

    public GameType getGametype() {
        return gametype;
    }

    public void setGametype(GameType gametype) {
        this.gametype = gametype;
    }


    @Override
    public String toString() {
        return "NewGameRequest{" +
                "gametype=" + gametype +
                '}';
    }
}
