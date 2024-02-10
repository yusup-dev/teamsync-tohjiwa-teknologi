package com.tohjiwa.teamsync.server.config.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyPathProperties(
        String publicKey,
        String privateKey
) {
}
