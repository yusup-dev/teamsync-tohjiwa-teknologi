package com.tohjiwa.teamsync.server.config.spring;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RsaKeyPathProperties.class)
public class ConfigurationProperties {

}
