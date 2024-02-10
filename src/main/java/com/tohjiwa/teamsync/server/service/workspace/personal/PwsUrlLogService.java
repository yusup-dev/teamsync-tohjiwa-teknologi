package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUrlLogRepository;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUrlLogEntity;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUrlLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PwsUrlLogService {
    @Autowired
    PwsUrlLogRepository pwsUrlLogRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<PwsUrlLog> addAll(List<PwsUrlLog> pwsUrlLogs) {
        List<PwsUrlLogEntity> pwsUrlLogEntities = new ArrayList<>();
        for (var pwsUrlLog : pwsUrlLogs) {
            var pwsUrlLogEntity = modelMapper.map(pwsUrlLog, PwsUrlLogEntity.class);
            pwsUrlLogEntity.setStatus(Status.ACTIVE);
            pwsUrlLogEntities.add(pwsUrlLogEntity);
        }

        var pwsUrlLogEntitiesRsl = pwsUrlLogRepository.saveAll(pwsUrlLogEntities);

        List<PwsUrlLog> pwsUrlLogsRsl = new ArrayList<>();
        pwsUrlLogEntitiesRsl.forEach(pwsUrlLogEntity -> pwsUrlLogsRsl.add(modelMapper.map(pwsUrlLogEntity, PwsUrlLog.class)));
        return pwsUrlLogsRsl;
    }
}
