package com.kamilcodemate.todoserver.helpers;

import com.kamilcodemate.todoserver.config.RsaKeyProperties;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;





@Slf4j
@Component
public class CheckJwtToken {

   private RsaKeyProperties keyPair;
Logger logger = LoggerFactory.getLogger(CheckJwtToken.class);

    public CheckJwtToken(RsaKeyProperties keyPair) {
        this.keyPair = keyPair;
    }


    public Claims checkJwt(String token, String username) throws InvalidTokenException {

            Claims claims = Jwts.parser().setSigningKey(keyPair.publicKey()).parseClaimsJws(token).getBody();
            if(!(claims.getSubject().equals(username))) {
             throw new InvalidTokenException("Invalid token");
            }
            return claims;





    }
}
