package com.tohjiwa.teamsync.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.controller.api.client.desktop.VersionController;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsAppRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserAppRepository;
import com.tohjiwa.teamsync.server.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    private final PwsAppRepository pwsAppRepository;
    private final PwsUserAppRepository pwsUserAppRepository;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AppService(PwsAppRepository pwsAppRepository, PwsUserAppRepository pwsUserAppRepository, UserService userService, ObjectMapper objectMapper) {
        this.pwsAppRepository = pwsAppRepository;
        this.pwsUserAppRepository = pwsUserAppRepository;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public List<App> getApps(Long userId, Long pwsId) {
        var pwsUserConfig = userService.getPwsUserConfig(userId, pwsId);

        Optional<List<App>> apps = Optional.empty();
        if (pwsUserConfig.isSpecificApp()) {
            var r1 = pwsUserAppRepository.findByUser_IdAndPws_Id(userId, pwsId);
            if (r1.isPresent()) {
                apps = Optional.ofNullable(r1.get().getApps());
            } else {
                var r2 = pwsAppRepository.findByPws_Id(pwsId);
                if (r2.isPresent()) {
                    apps = Optional.ofNullable(r2.get().getApps());
                }
            }
        } else {
            var r2 = pwsAppRepository.findByPws_Id(pwsId);
            if (r2.isPresent()) {
                apps = Optional.ofNullable(r2.get().getApps());
            }
        }

        if (apps.isPresent()) {
            return apps.get();
        } else {
            ClassLoader classLoader = VersionController.class.getClassLoader();

            var file = classLoader.getResourceAsStream("default-value/apps.json");
            try {
                return objectMapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
