package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.AuthResDTO;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.model.SecurityUser;
import com.tohjiwa.teamsync.server.service.spring.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service("API-Client-Desktop-Auth")
@RestController
@RequestMapping("/api/client/desktop/v1/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    @Autowired
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<RestResponseBody<?>> token(HttpServletRequest req, Authentication authentication) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        var principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser securityUser) {
            String token = tokenService.generateToken(authentication);
            logger.debug("Token requested for user: '{}'", authentication.getName());
            logger.debug("Token granted: {}", token);

            var authResDTO = new AuthResDTO(securityUser.user().getId(), token);
            var restResponseBody = new RestResponseBody<>(200, authResDTO);
            restResponseBody.setPath(req.getRequestURI());
            return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
        } else {
            throw new RuntimeException("failed auth");
        }
    }
}
