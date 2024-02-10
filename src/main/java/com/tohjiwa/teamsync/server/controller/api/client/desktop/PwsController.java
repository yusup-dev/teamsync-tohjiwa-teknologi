package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.HashResDTO;
import com.tohjiwa.teamsync.server.dto.workspace.personal.response.PwsUserResDTO;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsSetting;
import com.tohjiwa.teamsync.server.service.workspace.personal.PwsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
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

import java.util.ArrayList;
import java.util.List;

@Service("API-Client-Desktop-Pws")
@RestController
@RequestMapping("/api/client/desktop/v1/data/pws")
public class PwsController {
    @Autowired
    PwsService pwsService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/available")
    public ResponseEntity<RestResponseBody<?>> available(HttpServletRequest req, Authentication authentication) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var pwsUsers = pwsService.getPwsUser(userId);

        List<PwsUserResDTO> pwsUserResDTOs = new ArrayList<>();
        pwsUsers.forEach(pwsUser -> pwsUserResDTOs.add(modelMapper.map(pwsUser, PwsUserResDTO.class)));

        var restResponseBody = new RestResponseBody<>(200, pwsUserResDTOs);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @GetMapping("setting/hash")
    public ResponseEntity<RestResponseBody<?>> settingHash(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam Long pwsId) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var hashResDTO = new HashResDTO(pwsService.getSetting(userId, pwsId).hashCode());
        var restResponseBody = new RestResponseBody<>(200, hashResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @GetMapping("setting/")
    public ResponseEntity<RestResponseBody<?>> setting(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam Long pwsId) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var pwsSetting = pwsService.getSetting(userId, pwsId);

        var restResponseBody = new RestResponseBody<>(200, pwsSetting);
        restResponseBody.setHashCode(pwsSetting.hashCode());
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }
}
