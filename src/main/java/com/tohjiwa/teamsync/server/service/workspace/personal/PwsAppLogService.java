package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsAppLogRepository;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsAppLogEntity;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsAppLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PwsAppLogService {
    @Autowired
    PwsAppLogRepository pwsAppLogRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<PwsAppLog> addAll(List<PwsAppLog> pwsAppLogs) {
        List<PwsAppLogEntity> pwsAppLogEntities = new ArrayList<>();

        for (var pwsAppLog : pwsAppLogs) {
            var pwsAppLogEntity = modelMapper.map(pwsAppLog, PwsAppLogEntity.class);
            pwsAppLogEntity.setStatus(Status.ACTIVE);
            pwsAppLogEntities.add(pwsAppLogEntity);
        }

        var pwsAppLogEntitiesRsl = pwsAppLogRepository.saveAll(pwsAppLogEntities);

        List<PwsAppLog> pwsAppLogsRsl = new ArrayList<>();
        for (var pwsAppLogEntity : pwsAppLogEntitiesRsl) {
            pwsAppLogsRsl.add(modelMapper.map(pwsAppLogEntity, PwsAppLog.class));
        }
        return pwsAppLogsRsl;
    }
}
