package com.revature.mathtagon.auth;

import com.revature.mathtagon.auth.dtos.requests.LoginRequest;
import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.util.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
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

        if(req.getUsername().equals("admin") && req.getPassword().equals("revature"))
            p = new Principal(UUID.randomUUID().toString(), req.getUsername());
        else throw new RuntimeException("Access denied");

        String token = mTokenService.generateToken(p);
        resp.setHeader("Authorization", token);
        return p;
    }
}
