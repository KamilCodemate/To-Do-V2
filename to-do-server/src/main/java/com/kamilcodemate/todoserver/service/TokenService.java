package com.kamilcodemate.todoserver.service;


import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }



   public String generateToken(User user)
   {
       Instant now = Instant.now();
       String role = user.getRole();

       JwtClaimsSet claimsSet = JwtClaimsSet.builder()
               .issuer("self")
               .issuedAt(now)
               .expiresAt(now.plus(3, ChronoUnit.HOURS))
               .claim("role", role)
               .build();

       return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

   }
}
