package com.example.backend.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {
    @Value("${security.token.secret.admin}")
    private String secretKey;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

            return subject;
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}