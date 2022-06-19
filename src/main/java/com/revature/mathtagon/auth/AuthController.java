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

    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Principal login(@RequestBody LoginRequest req, HttpServletResponse resp) {
        Principal p;

        try {
            logger.info("Logging in with credentials " + req.getUsername() + ", " + req.getPassword());
            if (req.getUsername().equals("admin") && req.getPassword().equals("revature")) {
                logger.info("Authentication successful");

                p = new Principal(UUID.randomUUID().toString(), req.getUsername());
                resp.setHeader("Authorization", mTokenService.generateToken(p));
                resp.setStatus(HttpServletResponse.SC_OK);
                return p;
            }
            else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                logger.warning("\n==========\nException Thrown\n==========");
                throw new LoginException("Access denied fool");
            }
        } catch(LoginException le) {
            logger.severe(ExceptionUtils.getStackTrace(le));
            return null;
        }
    }
}
