package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.HashResDTO;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.service.AppService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service("API-Client-Desktop-App")
@RestController
@RequestMapping("/api/client/desktop/v1/data/apps")
public class AppController {
    @Autowired
    AppService appService;

    @GetMapping("/hash")
    public ResponseEntity<RestResponseBody<?>> hash(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam Long pwsId) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var hashResDTO = new HashResDTO(appService.getApps(userId, pwsId).hashCode());

        var restResponseBody = new RestResponseBody<>(200, hashResDTO);
        restResponseBody.setPath(req.getRequestURI());

        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<RestResponseBody<?>> list(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam Long pwsId) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var apps = appService.getApps(userId, pwsId);

        var restResponseBody = new RestResponseBody<>(200, apps);
        restResponseBody.setHashCode(apps.hashCode());
        restResponseBody.setPath(req.getRequestURI());

        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }
}
