package com.tohjiwa.teamsync.server.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorRef")
public class AuditConfiguration {
    @Bean
    public SpringSecurityAuditorAware auditorRef() {
        return new SpringSecurityAuditorAware();
    }
}
