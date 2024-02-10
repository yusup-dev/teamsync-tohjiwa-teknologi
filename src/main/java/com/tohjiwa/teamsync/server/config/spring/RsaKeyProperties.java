package com.tohjiwa.teamsync.server.config.spring;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public record RsaKeyProperties(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
) {
}
