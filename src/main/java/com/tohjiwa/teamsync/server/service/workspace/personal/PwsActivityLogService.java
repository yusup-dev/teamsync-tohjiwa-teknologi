package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsActivityLogRepository;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsActivityLogEntity;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsActivityLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PwsActivityLogService {
    @Autowired
    PwsActivityLogRepository pwsActivityLogRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<PwsActivityLog> addAll(List<PwsActivityLog> pwsActivityLogs) {
        List<PwsActivityLogEntity> pwsActivityLogEntities = new ArrayList<>();

        for (var pwsActivityLog : pwsActivityLogs) {
            var pwsActivityLogEntity = modelMapper.map(pwsActivityLog, PwsActivityLogEntity.class);
            pwsActivityLogEntity.setStatus(Status.ACTIVE);
            pwsActivityLogEntities.add(pwsActivityLogEntity);
        }

        var pwsActivityLogEntitiesRsl = pwsActivityLogRepository.saveAll(pwsActivityLogEntities);

        List<PwsActivityLog> pwsActivityLogsRsl = new ArrayList<>();
        for (var pwsActivityLogEntity : pwsActivityLogEntitiesRsl) {
            pwsActivityLogsRsl.add(modelMapper.map(pwsActivityLogEntity, PwsActivityLog.class));
        }
        return pwsActivityLogsRsl;
    }
}
