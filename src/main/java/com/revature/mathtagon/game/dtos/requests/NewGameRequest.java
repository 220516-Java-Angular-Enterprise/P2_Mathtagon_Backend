package com.revature.mathtagon.game.dtos.requests;


public class NewGameRequest {
    private Integer gametype;

    NewGameRequest(){super();}

    NewGameRequest(int gametype){
        this.gametype = gametype;
    }

    public int getGametype() {
        return gametype;
    }

    public void setGametype(int gametype) {
        this.gametype = gametype;
    }


    @Override
    public String toString() {
        return "NewGameRequest{" +
                "gametype=" + gametype +
                '}';
    }
}
