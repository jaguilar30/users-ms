package com.bci.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private JwtUtil(){}

    public static String getToken(String user, Integer expired){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setSubject("usuario123")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .signWith(key)
                .compact();
    }
}
