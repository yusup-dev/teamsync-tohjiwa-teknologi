package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.DesktopLastVerResDTO;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.service.VersionService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service("API-Client-Desktop-Version")
@RestController
@RequestMapping("/api/client/desktop/v1/utils/version")
public class VersionController {
    @Autowired
    VersionService versionService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/last")
    public ResponseEntity<RestResponseBody<?>> screenshot(HttpServletRequest req, Authentication authentication) throws Exception {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var desktopLastVerResDTO = modelMapper.map(versionService.getDesktopLastVer(), DesktopLastVerResDTO.class);
        var restResponseBody = new RestResponseBody<>(200, desktopLastVerResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }
}
