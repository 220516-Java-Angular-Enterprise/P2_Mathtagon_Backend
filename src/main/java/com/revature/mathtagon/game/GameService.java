package com.revature.mathtagon.game;

import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.game.dtos.requests.NewGameRequest;
import com.revature.mathtagon.game.dtos.requests.NewScoreRequest;
import com.revature.mathtagon.user.User;
import com.revature.mathtagon.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public User addPoints(NewScoreRequest request){
        User game = request.takeUser();
        gameRepository.record(request.getScore(),request.getGametype().ordinal(), request.getUserID());
        return game;
    }

    public NewGameRequest makeNewGame( NewGameRequest request,Principal token){

        gameRepository.createGame(request.getGametype().ordinal(),token.getId());
        return request;
    }
}
