package com.zoody.servinglife.ms_02_Location.JJWT;



import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


/**
 * JWT Utility class to validate token , signed by our secret key
 */
@Component
public class JwtUtil {



    @Value("${jwt.secret}") private String key ;

    private SecretKey SECRET_KEY;
    @PostConstruct
    public void init() {
         this.SECRET_KEY = Keys.hmacShaKeyFor(key.getBytes());
    }





    public String generateToken(String userId){

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *15))//15 mins
                .signWith(SECRET_KEY ,SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }



    public String extractUsername(String token){
       return Jwts.parserBuilder()
               .setSigningKey(SECRET_KEY)
               .build()
               .parseClaimsJws(token)
               .getBody()
               .getSubject();

    }

    private boolean isTokenExpired(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }


}
