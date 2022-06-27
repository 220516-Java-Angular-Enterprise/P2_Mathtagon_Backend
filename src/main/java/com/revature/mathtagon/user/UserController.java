package com.revature.mathtagon.user;


import com.revature.mathtagon.auth.TokenService;
import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.user.dtos.requests.NewUserRequest;
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
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Inject
    private final UserService userService;
    @Inject
    private final TokenService tokenService;

    @Inject
    @Autowired
    public UserController(UserService userService, TokenService tokenService){
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @GetMapping
    public @ResponseBody User getUserHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String request){
        Principal principal = tokenService.getRequesterDetails(request);
        //if(principal.equals(""))  userService.getUserHistory(principal);
        return userService.getUserHistory(principal);
    }




    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String register(@RequestBody NewUserRequest request) throws ResourceConflictException{
        logger.info("Creating user ");

        return userService.registerUser(request).getUserID();

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody Map<String, Object> handleResourceConflictException(ResourceConflictException le){
        logger.warning("\n=========\nException Thrown\n========");
        logger.severe(ExceptionUtils.getStackTrace(le));
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpServletResponse.SC_CONFLICT);
        responseBody.put("message", le.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Map<String, Object> handleBadRequestException(InvalidRequestException le){
        logger.warning("\n==========\nException Thrown\n========");
        logger.severe(ExceptionUtils.getStackTrace(le));
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("status", HttpServletResponse.SC_BAD_REQUEST);
        responseBody.put("message", le.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());
        return responseBody;
    }


}
