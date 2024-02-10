package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.LastOperation;
import com.tohjiwa.teamsync.server.constant.Source;
import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsTaskRepository;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsProjectEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsTaskEntity;
import com.tohjiwa.teamsync.server.exception.InvalidRequestException;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsTask;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsTaskSyncDt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PwsTaskService {
    @Autowired
    PwsTaskRepository pwsTaskRepository;
    @Autowired
    ModelMapper modelMapper;

    public PwsTask create(PwsTask pwsTask) {
        var pwsTaskEntity = modelMapper.map(pwsTask, PwsTaskEntity.class);
        pwsTaskEntity.setLastOperation(LastOperation.CREATE);
        pwsTaskEntity.setStatus(Status.ACTIVE);

        var pwsTaskEntityRsl = pwsTaskRepository.save(pwsTaskEntity);

        return modelMapper.map(pwsTaskEntityRsl, PwsTask.class);
    }

    public PwsTask update(Long pwsTaskId, String name, String description) {
        var pwsTaskEntity = pwsTaskRepository.getReferenceById(pwsTaskId);
        if (pwsTaskEntity.getSource() == Source.DESKTOP_APP_CLIENT && name != null) {
            pwsTaskEntity.setName(name);
        }

        if (description != null) {
            pwsTaskEntity.setDescription(description);
        }

        pwsTaskEntity.setLastOperation(LastOperation.UPDATE);

        var pwsTaskEntityRsl = pwsTaskRepository.save(pwsTaskEntity);
        return modelMapper.map(pwsTaskEntityRsl, PwsTask.class);
    }

    public void delete(Long pwsTaskId) {
        var pwsTaskEntity = pwsTaskRepository.getReferenceById(pwsTaskId);
        if (pwsTaskEntity.getSource() == Source.AUTO_DEFAULT) {
            throw new InvalidRequestException("Default task can't be delete");
        }

        pwsTaskRepository.deleteById(pwsTaskId);
    }

    public List<PwsTaskSyncDt> getAllTask(Long userId, Long pwsId) {
        var pwsTaskEntities = pwsTaskRepository.findByUser_IdAndPws_Id_Sync(userId, pwsId);

        List<PwsTaskSyncDt> pwsTaskSyncDts = new ArrayList<>();
        pwsTaskEntities.forEach(pwsTaskEntity -> pwsTaskSyncDts.add(modelMapper.map(pwsTaskEntity, PwsTaskSyncDt.class)));
        return pwsTaskSyncDts;
    }

    public List<PwsTaskSyncDt> getLastTask(Long userId, Long pwsId, Date ltuT) {
        var pwsTaskEntities = pwsTaskRepository.findByUser_IdAndPws_IdAndLastModifiedDateGreaterThan_Sync(userId, pwsId, ltuT);

        List<PwsTaskSyncDt> pwsTaskSyncDts = new ArrayList<>();
        pwsTaskEntities.forEach(pwsTaskEntity -> pwsTaskSyncDts.add(modelMapper.map(pwsTaskEntity, PwsTaskSyncDt.class)));
        return pwsTaskSyncDts;
    }

    public PwsTask createDefault(Long userId, Long pwsId, Long projectId) {
        var pwsTaskEntity = PwsTaskEntity.builder()
                .user(UserEntity.builder().id(userId).build())
                .pws(PwsEntity.builder().id(pwsId).build())
                .pwsProject(PwsProjectEntity.builder().id(projectId).build())
                .name("Default")
                .description("-")
                .lastOperation(LastOperation.CREATE)
                .source(Source.AUTO_DEFAULT)
                .build();

        var pwsTaskEntityRsl = pwsTaskRepository.save(pwsTaskEntity);

        return modelMapper.map(pwsTaskEntityRsl, PwsTask.class);
    }

}
