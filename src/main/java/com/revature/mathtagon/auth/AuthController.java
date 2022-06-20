package com.revature.mathtagon.auth;

import com.revature.mathtagon.auth.dtos.requests.LoginRequest;
import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.util.annotations.Inject;
import com.revature.mathtagon.util.customexceptions.AuthenticationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class.getName());
    @Inject
    private final TokenService mTokenService;

    @Inject
    @Autowired
    public AuthController(TokenService tServ) {
        mTokenService = tServ;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Principal login(@RequestBody LoginRequest req, HttpServletResponse resp) throws LoginException {
        logger.info("Logging in with credentials " + req.getUsername() + ", " + req.getPassword());

        Principal p;
        if(req.getUsername().equals("admin") && req.getPassword().equals("revature"))
            p =  new Principal(UUID.randomUUID().toString(), req.getUsername());
        else throw new LoginException("Access denied fool");

        logger.info("Authentication successful");
        resp.setHeader("Authorization", mTokenService.generateToken(p));
        return p;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody Map<String, Object> handleLoginException(LoginException le) {
        logger.warning("\n==========\nException Thrown\n==========");
        logger.severe(ExceptionUtils.getStackTrace(le));

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        responseBody.put("message", le.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());

        return responseBody;
    }
}
