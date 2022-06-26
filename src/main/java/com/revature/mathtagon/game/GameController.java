package com.revature.mathtagon.game;

import com.revature.mathtagon.auth.TokenService;
import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.game.dtos.requests.NewGameRequest;

import com.revature.mathtagon.game.dtos.requests.NewSaveRequest;
import com.revature.mathtagon.game.dtos.responses.GameConfirmation;

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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE, params = {"new-game"})
    public @ResponseBody GameConfirmation makeGame(
            @RequestBody NewGameRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token)
    {
        Principal principal = tokenService.getRequesterDetails(token);
        logger.info("Making a new game. Pricipal acquired:\n"+principal);
        User user = userService.getByUsername(principal.getUsername());

        return new GameConfirmation(new Game(user, request));

    }

    //Post a users stats with their userID
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Game postPoints(@RequestBody NewSaveRequest request) throws ResourceConflictException{
        User u = userService.getByID(request.getUserID());
        logger.info("Posting stats for user\n"+u);

        return gameService.saveGame(u, request);
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
