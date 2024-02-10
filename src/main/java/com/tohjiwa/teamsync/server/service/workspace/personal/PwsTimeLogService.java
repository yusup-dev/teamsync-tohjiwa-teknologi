package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsTimeLogRepository;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsTimeLogEntity;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsTimeLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PwsTimeLogService {
    private final PwsTimeLogRepository pwsTimeLogRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PwsTimeLogService(PwsTimeLogRepository pwsTimeLogRepository, ModelMapper modelMapper) {
        this.pwsTimeLogRepository = pwsTimeLogRepository;
        this.modelMapper = modelMapper;
    }

    public List<PwsTimeLog> addAll(List<PwsTimeLog> pwsTimeLogs) {
        List<PwsTimeLogEntity> pwsTimeLogEntities = new ArrayList<>();

        for (var pwsTimeLog : pwsTimeLogs) {
            var pwsTimeLogEntity = modelMapper.map(pwsTimeLog, PwsTimeLogEntity.class);
            pwsTimeLogEntity.setStatus(Status.ACTIVE);
            pwsTimeLogEntities.add(pwsTimeLogEntity);
        }

        var pwsTimeLogEntitiesRsl = pwsTimeLogRepository.saveAll(pwsTimeLogEntities);

        List<PwsTimeLog> pwsTimeLogsRsl = new ArrayList<>();
        for (var pwsTimeLogEntity : pwsTimeLogEntitiesRsl) {
            pwsTimeLogsRsl.add(modelMapper.map(pwsTimeLogEntity, PwsTimeLog.class));
        }
        return pwsTimeLogsRsl;
    }
}
