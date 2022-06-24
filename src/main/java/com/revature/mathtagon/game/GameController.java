package com.revature.mathtagon.game;

import com.revature.mathtagon.auth.TokenService;
import com.revature.mathtagon.game.dtos.requests.NewScoreRequest;
import com.revature.mathtagon.util.annotations.Inject;
import com.revature.mathtagon.util.customexceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.attribute.standard.Media;
import java.util.logging.Logger;

@RestController
@RequestMapping("/games")
public class GameController {
    private static final Logger logger = Logger.getLogger(GameController.class.getName());

    @Inject
    private final GameService gameService;
    private final TokenService tokenService;


    @Inject
    @Autowired
    public GameController(GameService gameService, TokenService tokenService) {
        this.gameService = gameService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String postPoints(@RequestBody NewScoreRequest request) throws ResourceConflictException{
        logger.info("Posting stats");

        return gameService.addPoints(request).getUserID();
    }
}
