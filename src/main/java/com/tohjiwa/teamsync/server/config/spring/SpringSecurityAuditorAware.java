package com.tohjiwa.teamsync.server.config.spring;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof Jwt) {
            var jwt = (Jwt) authentication.getPrincipal();
            Long userId = jwt.getClaim("userId");
            return Optional.of("userId:" + userId);
        } else if (authentication.getPrincipal() instanceof String) {
            var str = (String) authentication.getPrincipal();
            return Optional.of(str);
        }

        return Optional.empty();
    }
}
