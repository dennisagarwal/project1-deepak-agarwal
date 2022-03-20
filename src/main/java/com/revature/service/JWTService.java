package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTService {

    private Key key;

    //Instance initialization block
    //This will run immediately after the cinstruction

    {
        byte[] secret = "my_secret_password_my_secret_password_my_secret_password_my_secret_password".getBytes();
            key = Keys.hmacShaKeyFor(secret); //create a key using
    }

    //Signing a JWT with the key
    public String createJWT(User user){
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("user_id", user.getId())
                .claim("user_role", user.getUserRole())
                .signWith(key)
                .compact();

    return jwt;
    }

    //validating a JWT with the key
    public void validateJwt(String jwt){
        try{
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
//            System.out.println(token.getBody());
            String username = token.getBody().getSubject();
            Integer userId = token.getBody().get("user_id", Integer.class);
            String userRole = token.getBody().get("user_role",String.class);

            System.out.println(username);
            System.out.println(userId);
            System.out.println(userRole);

        }catch(JwtException e){
            e.printStackTrace();
            throw new UnauthorizedResponse("JWT was invalid");
        }
    }
}
