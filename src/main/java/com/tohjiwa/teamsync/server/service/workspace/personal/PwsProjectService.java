package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.LastOperation;
import com.tohjiwa.teamsync.server.constant.Source;
import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsProjectRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsTaskRepository;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsProjectEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsTaskEntity;
import com.tohjiwa.teamsync.server.exception.InvalidRequestException;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsProject;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsProjectSyncDt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PwsProjectService {
    @Autowired
    PwsProjectRepository pwsProjectRepository;
    @Autowired
    PwsTaskRepository pwsTaskRepository;
    @Autowired
    PwsTaskService pwsTaskService;
    @Autowired
    ModelMapper modelMapper;

    public List<PwsProjectSyncDt> getAllProject_Sync(Long userId, Long pwsId) {
        var pwsProjectEntities = pwsProjectRepository.findByUser_IdAndPws_Id_Sync(userId, pwsId);
        if (pwsProjectEntities.size() < 1) { // when not have a default project, create a default project and return it
            var pwsProjectEntityRsl = createDefaultProject(userId, pwsId);
            pwsProjectEntities.add(pwsProjectEntityRsl);
        }

        List<PwsProjectSyncDt> pwsProjectSyncDts = new ArrayList<>();
        pwsProjectEntities.forEach(pwsProjectEntity -> pwsProjectSyncDts.add(modelMapper.map(pwsProjectEntity, PwsProjectSyncDt.class)));
        return pwsProjectSyncDts;
    }

    public List<PwsProjectSyncDt> getLastProject(Long userId, Long pwsId, Date lpuT) {
        List<PwsProjectEntity> pwsProjectEntities;

        if (lpuT.getTime() < 1) {
            pwsProjectEntities = pwsProjectRepository.findByUser_IdAndPws_Id_Sync(userId, pwsId);
            if (pwsProjectEntities.size() < 1) { // when not have a default project, create a default project and return it
                var pwsProjectEntityRsl = createDefaultProject(userId, pwsId);
                pwsProjectEntities.add(pwsProjectEntityRsl);
            }
        } else {
            pwsProjectEntities = pwsProjectRepository.findByUser_IdAndPws_IdAndLastModifiedDateGreaterThan_Sync(userId, pwsId, lpuT);
        }

        List<PwsProjectSyncDt> pwsProjectSyncDts = new ArrayList<>();
        pwsProjectEntities.forEach(pwsProjectEntity -> pwsProjectSyncDts.add(modelMapper.map(pwsProjectEntity, PwsProjectSyncDt.class)));
        return pwsProjectSyncDts;
    }

    private PwsProjectEntity createDefaultProject(Long userId, Long pwsId) {
        var pwsProjectEntity = PwsProjectEntity.builder()
                .user(UserEntity.builder().id(userId).build())
                .pws(PwsEntity.builder().id(pwsId).build())
                .name("Default").description("-")
                .lastOperation(LastOperation.CREATE)
                .source(Source.AUTO_DEFAULT).build();
        var pwsProjectEntityRsl = pwsProjectRepository.save(pwsProjectEntity);

        var pwsTaskEntity = PwsTaskEntity.builder()
                .user(UserEntity.builder().id(userId).build())
                .pws(PwsEntity.builder().id(pwsId).build())
                .pwsProject(PwsProjectEntity.builder().id(pwsProjectEntityRsl.getId()).build())
                .name("Default").description("-").lastOperation(LastOperation.CREATE)
                .source(Source.AUTO_DEFAULT).build();
        pwsTaskRepository.save(pwsTaskEntity);

        return pwsProjectEntityRsl;
    }

    public PwsProject create(PwsProject pwsProject) {
        var pwsProjectEntity = modelMapper.map(pwsProject, PwsProjectEntity.class);
        pwsProjectEntity.setLastOperation(LastOperation.CREATE);
        pwsProjectEntity.setStatus(Status.ACTIVE);
        var pwsProjectEntityRsl = pwsProjectRepository.save(pwsProjectEntity);

        var x = pwsTaskService.createDefault(pwsProjectEntityRsl.getUser().getId(), pwsProjectEntityRsl.getPws().getId(), pwsProjectEntityRsl.getId());

        return modelMapper.map(pwsProjectEntityRsl, PwsProject.class);
    }

    public PwsProject update(Long pwsProjectId, String name, String description) {
        var pwsProjectEntity = pwsProjectRepository.findById(pwsProjectId);
        if(pwsProjectEntity.isEmpty()) {
            throw new InvalidRequestException("Can't update project, project with id " + pwsProjectId + " not found.");
        }

        if (pwsProjectEntity.get().getSource() == Source.DESKTOP_APP_CLIENT && name != null) {
            pwsProjectEntity.get().setName(name);
        }

        if (description != null) {
            pwsProjectEntity.get().setDescription(description);
        }

        pwsProjectEntity.get().setLastOperation(LastOperation.UPDATE);

        var pwsProjectEntityRsl = pwsProjectRepository.save(pwsProjectEntity.get());

        return modelMapper.map(pwsProjectEntityRsl, PwsProject.class);
    }

    public void delete(Long pwsProjectId) {
        var pwsProjectEntity = pwsProjectRepository.getReferenceById(pwsProjectId);
        if (pwsProjectEntity.getSource().equals(Source.AUTO_DEFAULT)) {
            throw new InvalidRequestException("Default project can't be delete");
        }

        pwsProjectRepository.deleteById(pwsProjectId);
        pwsTaskRepository.deleteByPwsProject(PwsProjectEntity.builder().id(pwsProjectId).build());
    }

}
