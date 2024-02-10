package com.tohjiwa.teamsync.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.controller.api.client.desktop.VersionController;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUrlRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserUrlRepository;
import com.tohjiwa.teamsync.server.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private final UserService userService;
    private final PwsUrlRepository pwsUrlRepository;
    private final PwsUserUrlRepository pwsUserUrlRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UrlService(UserService userService, PwsUrlRepository pwsUrlRepository, PwsUserUrlRepository pwsUserUrlRepository, ObjectMapper objectMapper) {
        this.userService = userService;
        this.pwsUrlRepository = pwsUrlRepository;
        this.pwsUserUrlRepository = pwsUserUrlRepository;
        this.objectMapper = objectMapper;
    }

    public List<Url> getUrls(Long userId, Long pwsId) {
        var pwsUserConfig = userService.getPwsUserConfig(userId, pwsId);

        Optional<List<Url>> urls = Optional.empty();
        if (pwsUserConfig.isSpecificUrl()) {
            var r1 = pwsUserUrlRepository.findByUser_IdAndPws_Id(userId, pwsId);
            if (r1.isPresent()) {
                urls = Optional.ofNullable(r1.get().getUrls());
            } else {
                var r2 = pwsUrlRepository.findByPws_Id(pwsId);
                if (r2.isPresent()) {
                    urls = Optional.ofNullable(r2.get().getUrls());
                }
            }
        } else {
            var r2 = pwsUrlRepository.findByPws_Id(pwsId);
            if (r2.isPresent()) {
                urls = Optional.ofNullable(r2.get().getUrls());
            }
        }

        if (urls.isPresent()) {
            return urls.get();
        } else {
            ClassLoader classLoader = VersionController.class.getClassLoader();

            var file = classLoader.getResourceAsStream("default-value/urls.json");
            try {
                return objectMapper.readValue(file, new TypeReference<List<Url>>() {
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
