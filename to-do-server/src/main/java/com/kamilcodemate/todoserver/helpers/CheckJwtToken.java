package com.kamilcodemate.todoserver.helpers;

import com.kamilcodemate.todoserver.config.RsaKeyProperties;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Bearer Token helper class
 */
@Slf4j
@Component
public class CheckJwtToken {

    /**
     * public and private RSA keypair
     */
   private final RsaKeyProperties keyPair;


    /** Initialize Bean
     * @param keyPair Initialization keypair value
     */
    public CheckJwtToken(RsaKeyProperties keyPair) {
        this.keyPair = keyPair;
    }


    /** Check Bearer Token correction
     * @param token Bearer Token to check
     * @param username Username that should be corresponded to Bearer Token
     * @return User claims
     * @throws InvalidTokenException Throws if token is invalid
     */
    public Claims checkJwt(String token, String username) throws InvalidTokenException {

            Claims claims = Jwts.parser().setSigningKey(keyPair.publicKey()).parseClaimsJws(token).getBody();
            System.out.println(username + " " + claims.getSubject());
            if(!(claims.getSubject().equals(username))) {
             throw new InvalidTokenException("Invalid token");
            }
            return claims;





    }
}
