package com.kamilcodemate.todoserver.config;


import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.
        HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.
        EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.
        AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * RSA keypair
     */
    private final RsaKeyProperties rsaKeys;

    /**
     * IOC constructor.
     *
     * @param rsaKeys RSA keypair
     */
    public SecurityConfig(final RsaKeyProperties rsaKeys) {
        this.rsaKeys = rsaKeys;
    }

    /**
     * Security Filter Chain configuration.
     *
     * @param httpSecurity HTTP Security parameter
     * @return HTTP Security configuration
     * @throws Exception Throws Spring internal exceptions
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity
                                                               httpSecurity)
            throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.
                        withDefaults()))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * Bcrypt configuration.
     *
     * @return Bcrypt configuration
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Nimbus JWT Decoder configuration.
     *
     * @return Nimbus JWT Decoder configuration
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.getPublicKey()).build();
    }

    /**
     * Nimbus JWT Encoder configuration.
     *
     * @return Nimbus JWT Encoder configuration
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.getPublicKey()).privateKey(rsaKeys
                .getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>
                (new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
