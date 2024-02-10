package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.controller.api.client.desktop.VersionController;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsSettingRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserSettingRepository;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsSetting;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUser;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
import com.tohjiwa.teamsync.server.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PwsService {
    @Autowired
    PwsUserRepository pwsUserRepository;
    @Autowired
    PwsSettingRepository pwsSettingRepository;
    @Autowired
    PwsUserSettingRepository pwsUserSettingRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserService userService;

    public List<PwsUser> getPwsUser(Long userId) {
        var pwsUserEntities = pwsUserRepository.findByUser_Id(userId);

        List<PwsUser> pwsUsers = new ArrayList<>();
        pwsUserEntities.forEach(pwsUserEntity -> pwsUsers.add(modelMapper.map(pwsUserEntity, PwsUser.class)));

        return pwsUsers;
    }

    public PwsSetting getSetting(Long userId, Long pwsId) {
        PwsUserConfig pwsUserConfig = userService.getPwsUserConfig(userId, pwsId);
        Optional<PwsSetting> pwsSetting = Optional.empty();
        if (pwsUserConfig.isSpecificPwsSetting()) {
            var r1 = pwsUserSettingRepository.findByUser_IdAndPws_Id(userId, pwsId);
            if (r1.isPresent()) {
                pwsSetting = Optional.ofNullable(r1.get().getPersonalWorkspaceSetting());
            } else {
                var r2 = pwsSettingRepository.findByPws_Id(pwsId);
                if (r2.isPresent()) {
                    pwsSetting = Optional.ofNullable(r2.get().getPersonalWorkspaceSetting());
                }
            }
        } else {
            var r2 = pwsSettingRepository.findByPws_Id(pwsId);
            if (r2.isPresent()) {
                pwsSetting = Optional.ofNullable(r2.get().getPersonalWorkspaceSetting());
            }
        }

        if (pwsSetting.isPresent()) {
            return pwsSetting.get();
        } else {
            ClassLoader classLoader = VersionController.class.getClassLoader();
            var file = classLoader.getResourceAsStream("default-value/company-setting.json");
            try {
                var companySetting = objectMapper.readValue(file, PwsSetting.class);
                companySetting.setPwsId(pwsId);
                return companySetting;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
