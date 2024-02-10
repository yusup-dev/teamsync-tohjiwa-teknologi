package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.UserResDTO;
import com.tohjiwa.teamsync.server.exception.InvalidRequestException;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service("API-Client-Desktop-User")
@RestController
@RequestMapping("/api/client/desktop/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<RestResponseBody<?>> hash(HttpServletRequest req, Authentication authentication) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var userRsl = userService.getUser(userId);
        if (userRsl.isEmpty()) {
            throw new InvalidRequestException("User ID Not Found");
        }

        var userResDTO = modelMapper.map(userRsl.get(), UserResDTO.class);

        var restResponseBody = new RestResponseBody<>(200, userResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }
}
