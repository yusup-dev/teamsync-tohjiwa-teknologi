package com.tohjiwa.teamsync.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.config.spring.RsaKeyPathProperties;
import com.tohjiwa.teamsync.server.config.spring.RsaKeyProperties;
import com.tohjiwa.teamsync.server.utils.JavaSecurityPemUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class BeanConfiguration {
    private final RsaKeyPathProperties rsaKeyPropertiesPath;

    @Autowired
    public BeanConfiguration(RsaKeyPathProperties rsaKeyPropertiesPath) {
        this.rsaKeyPropertiesPath = rsaKeyPropertiesPath;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RsaKeyProperties rsaKey() throws Exception {
        var publicKey = JavaSecurityPemUtils.readX509PublicKey(rsaKeyPropertiesPath.publicKey());
        var privateKey = JavaSecurityPemUtils.readPKCS8PrivateKey(rsaKeyPropertiesPath.privateKey());

        return new RsaKeyProperties(publicKey, privateKey);
    }
}
