package com.revature.mathtagon.auth;

import com.revature.mathtagon.auth.dtos.responses.Principal;
import com.revature.mathtagon.util.annotations.Inject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Inject
    private JwtConfig jwtConfig;

    public TokenService(){}

    @Inject
    @Autowired
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId())
                .setIssuer("mathtagon")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .claim("username", subject.getUsername())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public Principal getRequesterDetails(String token) {
        try {
            Claims claim = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            return new Principal(claim.getId(), claim.get("username", String.class));
        } catch (Exception e) {
            return null;
        }
    }
}
