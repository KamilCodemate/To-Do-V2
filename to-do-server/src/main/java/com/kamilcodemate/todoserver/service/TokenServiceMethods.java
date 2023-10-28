package com.kamilcodemate.todoserver.service;


import com.kamilcodemate.todoserver.entity.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Token helper methods
 */
@Service
public class TokenServiceMethods {


    /**
     * JWT encoder bean
     */
    private final JwtEncoder jwtEncoder;

    /** IOC constructor
     * @param jwtEncoder Bean to initalize
     */
    public TokenServiceMethods(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }


    /** Bearer Token generator
     * @param user User corresponding to generated Bearer Token
     * @return Generated Token
     */
   public String generateToken(User user)
   {
       Instant now = Instant.now();
       String role = user.getRole();

       JwtClaimsSet claimsSet = JwtClaimsSet.builder()
               .subject(user.getUsername())
               .issuer("self")
               .issuedAt(now)
               .expiresAt(now.plus(7, ChronoUnit.DAYS))
               .claim("role", role)
               .build();

       return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

   }
}
