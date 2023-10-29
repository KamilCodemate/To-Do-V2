package com.kamilcodemate.todoserver.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Class for KeyPair.
 */
@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {
    /**
     * public RSA Key
     */
    private RSAPublicKey publicKey;
    /**
     * private RSA key
     */
    private RSAPrivateKey privateKey;

}