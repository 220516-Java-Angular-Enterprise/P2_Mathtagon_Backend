package com.revature.mathtagon.game;


import com.revature.mathtagon.auth.dtos.responses.Principal;

import com.revature.mathtagon.game.dtos.requests.NewSaveRequest;

import com.revature.mathtagon.game.dtos.responses.GameConfirmation;
import com.revature.mathtagon.user.User;
import com.revature.mathtagon.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class GameService {
    private static final Logger logger = Logger.getLogger(GameService.class.getName());


    @Inject
    private final GameRepository gameRepository;

    @Inject
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game saveGame(User u, NewSaveRequest request){
        Game g = new Game(u, request.getGametype(), request.getScore());
        logger.info("Recording game:\n"+g+"\nWith request:\n"+request);
        gameRepository.record(g.getGametype().ordinal(), g.getScore(), g.getUser().getUserID());
        return g;
    }

    public List<Object[]> getTopFive(){
         List<Object[]> gameList = gameRepository.topFive();
         return gameList;
    }


}
