package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsScreenshotLogRepository;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsScreenshotLogEntity;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsScreenshot;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PwsScreenshotService {
    @Autowired
    PwsScreenshotLogRepository pwsScreenshotLogRepository;
    @Autowired
    ModelMapper modelMapper;

    public PwsScreenshot addAll(PwsScreenshot pwsScreenshot) {
        var pwsScreenshotLogEntity = modelMapper.map(pwsScreenshot, PwsScreenshotLogEntity.class);
        pwsScreenshotLogEntity.setStatus(Status.ACTIVE);

        var pwsScreenshotLogEntityRsl = pwsScreenshotLogRepository.save(pwsScreenshotLogEntity);
        return modelMapper.map(pwsScreenshotLogEntityRsl, PwsScreenshot.class);

    }
}
