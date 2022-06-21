package com.revature.mathtagon.auth;

import com.revature.mathtagon.auth.dtos.requests.LoginRequest;
import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.user.UserService;
import com.revature.mathtagon.util.annotations.Inject;
import com.revature.mathtagon.util.customexceptions.AuthenticationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class.getName());
    @Inject
    private final TokenService mTokenService;
    private final UserService mUserService;

    @Inject
    @Autowired
    public AuthController(TokenService tServ, UserService uServ) {
        mTokenService = tServ;
        mUserService = uServ;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Principal> login(@RequestBody LoginRequest req) throws AuthenticationException {
        logger.info("Logging in with credentials " + req.getUsername() + ", " + req.getPassword());
        Principal p = new Principal(mUserService.login(req));

        logger.info("Authentication successful");
        String jToken = mTokenService.generateToken(p);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jToken).body(p);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody Map<String, Object> handleAuthenticationException(AuthenticationException ae) {
        logger.warning("\n==========\nException Thrown\n==========");
        logger.severe(ExceptionUtils.getStackTrace(ae));

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        responseBody.put("message", ae.getMessage());
        responseBody.put("timestamp", LocalDateTime.now().toString());

        return responseBody;
    }
}
