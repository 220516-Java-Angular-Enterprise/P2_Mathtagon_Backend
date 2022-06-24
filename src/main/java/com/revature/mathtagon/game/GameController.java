package com.revature.mathtagon.game;

import com.revature.mathtagon.auth.TokenService;
import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.game.dtos.requests.NewGameRequest;
import com.revature.mathtagon.game.dtos.requests.NewScoreRequest;
import com.revature.mathtagon.user.User;
import com.revature.mathtagon.user.UserService;
import com.revature.mathtagon.util.annotations.Inject;
import com.revature.mathtagon.util.customexceptions.InvalidRequestException;
import com.revature.mathtagon.util.customexceptions.ResourceConflictException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/games")
public class GameController {
    private static final Logger logger = Logger.getLogger(GameController.class.getName());

    @Inject
    private final GameService gameService;
    private final TokenService tokenService;
    private final UserService userService;


    @Inject
    @Autowired
    public GameController(GameService gameService, TokenService tokenService, UserService userService) {
        this.gameService = gameService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    //Makes a New Game
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE, params = {"newGame"})
    public @ResponseBody
    Game makeGame(@RequestBody NewGameRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws ResourceConflictException{
        logger.info("Making a new game");
        Game game = new Game();
        Principal principal = tokenService.getRequesterDetails(token);
        userService.getUserHistory(principal);
        gameService.makeNewGame(request,principal);


        return game;
    }

    //Post a users stats with their userID
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String postPoints(@RequestBody NewScoreRequest request) throws ResourceConflictException{
        logger.info("Posting stats");

        return gameService.addPoints(request).getUserID();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody Map<String, Object> handleResourceConflictException(ResourceConflictException le){
        logger.warning("\n==========\nException Thrown\n==========");
        logger.severe(ExceptionUtils.getStackTrace(le));
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("status", HttpServletResponse.SC_CONFLICT);
        responseBody.put("message", le.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Map<String, Object> handleBadRequestException(InvalidRequestException le){
        logger.warning("\n==========\nException Thrown\n==========");
        logger.severe(ExceptionUtils.getStackTrace(le));
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("status", HttpServletResponse.SC_BAD_REQUEST);
        responseBody.put("message", le.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }

}
