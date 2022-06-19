package com.revature.mathtagon.auth;

import com.revature.mathtagon.util.annotations.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Inject
    private final TokenService mTokenService;
}
