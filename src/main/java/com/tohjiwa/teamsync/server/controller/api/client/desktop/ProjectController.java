package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.DeleteReqDTO;
import com.tohjiwa.teamsync.server.dto.workspace.personal.request.PwsProjectCrReqDTO;
import com.tohjiwa.teamsync.server.dto.workspace.personal.request.PwsProjectUpReqDTO;
import com.tohjiwa.teamsync.server.dto.workspace.personal.request.PwsTaskCrReqDTO;
import com.tohjiwa.teamsync.server.dto.workspace.personal.request.PwsTaskUpReqDTO;
import com.tohjiwa.teamsync.server.dto.workspace.personal.response.*;
import com.tohjiwa.teamsync.server.exception.InvalidRequestException;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsProject;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsProjectSyncDt;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsTask;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsTaskSyncDt;
import com.tohjiwa.teamsync.server.service.workspace.personal.PwsProjectService;
import com.tohjiwa.teamsync.server.service.workspace.personal.PwsTaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("API-Client-Desktop-Project")
@Validated
@RestController
@RequestMapping("/api/client/desktop/v1/data/projects")
public class ProjectController {
    @Autowired
    PwsProjectService pwsProjectService;
    @Autowired
    PwsTaskService pwsTaskService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<RestResponseBody<?>> list(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam long lpuT, @NotNull @RequestParam long ltuT, @NotNull @RequestParam int q, @NotNull @RequestParam Long pwsId) {

        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        List<PwsProjectSyncDt> pwsProjects;
        List<PwsTaskSyncDt> pwsTasks;

        switch (q) {
            case 0 -> {
                pwsProjects = pwsProjectService.getLastProject(userId, pwsId, new Date(lpuT));
                pwsTasks = pwsTaskService.getLastTask(userId, pwsId, new Date(ltuT));
            }
            case 1 -> {
                pwsProjects = pwsProjectService.getAllProject_Sync(userId, pwsId);
                pwsTasks = pwsTaskService.getAllTask(userId, pwsId);
            }
            default -> throw new InvalidRequestException("q with not a valid query value");
        }

        List<PwsProjectSyncDtResDTO> pwsProjectResDTOs = new ArrayList<>();
        pwsProjects.forEach(pwsProject -> pwsProjectResDTOs.add(modelMapper.map(pwsProject, PwsProjectSyncDtResDTO.class)));

        List<PwsTaskSyncDtResDTO> pwsTaskResDTOs = new ArrayList<>();
        pwsTasks.forEach(pwsTask -> pwsTaskResDTOs.add(modelMapper.map(pwsTask, PwsTaskSyncDtResDTO.class)));

        var pwsProjectSyncResDTO = new PwsProjectSyncResDTO(pwsProjectResDTOs, pwsTaskResDTOs);

        var restResponseBody = new RestResponseBody<>(200, pwsProjectSyncResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RestResponseBody<?>> addProject(HttpServletRequest req, Authentication authentication, @Valid @RequestBody PwsProjectCrReqDTO pwsProjectCrReqDTO) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        var pwsProject = new PwsProject();
        BeanUtils.copyProperties(pwsProjectCrReqDTO, pwsProject);
        var pwsProjectRsl = pwsProjectService.create(pwsProject);

        var pwsProjectResDTO = modelMapper.map(pwsProjectRsl, PwsProjectResDTO.class);

        var restResponseBody = new RestResponseBody<>(200, pwsProjectResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<RestResponseBody<?>> updateProject(HttpServletRequest req, Authentication authentication, @Valid @RequestBody PwsProjectUpReqDTO pwsProjectUpReqDTO) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        var pwsProject = pwsProjectService.update(pwsProjectUpReqDTO.id(), pwsProjectUpReqDTO.name(), pwsProjectUpReqDTO.description());
        var pwsProjectResDTO = modelMapper.map(pwsProject, PwsProjectResDTO.class);

        var restResponseBody = new RestResponseBody<>(200, pwsProjectResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<RestResponseBody<Object>> deleteProject(HttpServletRequest req, Authentication authentication, @Valid @RequestBody DeleteReqDTO deleteReqDTO) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        pwsProjectService.delete(deleteReqDTO.id());

        var restResponseBody = new RestResponseBody<>(200, null);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<RestResponseBody<?>> addTask(HttpServletRequest req, Authentication authentication, @Valid @RequestBody PwsTaskCrReqDTO pwsTaskCrReqDTO) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        var pwsTask = new PwsTask();
        BeanUtils.copyProperties(pwsTaskCrReqDTO, pwsTask);
        var pwsTaskRsl = pwsTaskService.create(pwsTask);

        var pwsTaskResDTO = modelMapper.map(pwsTaskRsl, PwsTaskResDTO.class);

        var restResponseBody = new RestResponseBody<>(200, pwsTaskResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PutMapping("/tasks")
    public ResponseEntity<RestResponseBody<?>> updateTask(HttpServletRequest req, Authentication authentication, @Valid @RequestBody PwsTaskUpReqDTO pwsTaskUpReqDTO) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        var pwsTask = pwsTaskService.update(pwsTaskUpReqDTO.id(), pwsTaskUpReqDTO.name(), pwsTaskUpReqDTO.description());
        var pwsProjectResDTO = modelMapper.map(pwsTask, PwsProjectResDTO.class);

        var restResponseBody = new RestResponseBody<>(200, pwsProjectResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<RestResponseBody<?>> deleteTask(HttpServletRequest req, Authentication authentication, @Valid @RequestBody DeleteReqDTO deleteReqDTO) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        pwsTaskService.delete(deleteReqDTO.id());

        var restResponseBody = new RestResponseBody<>(200, null);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

}
