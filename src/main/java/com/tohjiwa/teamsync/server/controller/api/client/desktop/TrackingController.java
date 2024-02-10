package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.workspace.personal.request.*;
import com.tohjiwa.teamsync.server.dto.workspace.personal.response.*;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.model.workspace.personal.*;
import com.tohjiwa.teamsync.server.service.workspace.personal.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service("API-Client-Desktop-Tracking")
@Validated
@RestController
@RequestMapping("/api/client/desktop/v1/tracking")
public class TrackingController {
    @Autowired
    PwsActivityLogService pwsActivityLogService;
    @Autowired
    PwsAppLogService pwsAppLogService;
    @Autowired
    PwsScreenshotService pwsScreenshotService;
    @Autowired
    PwsUrlLogService pwsUrlLogService;
    private final PwsTimeLogService pwsTimeLogsService;
    private final ModelMapper modelMapper;

    @Autowired
    public TrackingController(PwsTimeLogService pwsTimeLogsService, ModelMapper modelMapper) {
        this.pwsTimeLogsService = pwsTimeLogsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/activity-logs")
    public ResponseEntity<RestResponseBody<?>> activityLog(HttpServletRequest req, Authentication authentication, @RequestBody List<@Valid PwsActivityLogCrReqDTO> pwsActivityLogCrReqDTOs) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        List<PwsActivityLog> pwsActivityLogs = new ArrayList<>();
        for (var pwsActivityLogCrReqDTO : pwsActivityLogCrReqDTOs) {
            var pwsActivityLog = new PwsActivityLog();
            BeanUtils.copyProperties(pwsActivityLogCrReqDTO, pwsActivityLog);
            pwsActivityLogs.add(pwsActivityLog);
        }
        var pwsActivityRsl = pwsActivityLogService.addAll(pwsActivityLogs);

        List<PwsActivityLogResDTO> pwsActivityLogResDTOs = new ArrayList<>();
        for (var pwsActivityLog : pwsActivityRsl) {
            pwsActivityLogResDTOs.add(modelMapper.map(pwsActivityLog, PwsActivityLogResDTO.class));
        }

        var restResponseBody = new RestResponseBody<>(200, pwsActivityLogResDTOs);
        restResponseBody.setPath(req.getRequestURI());

        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PostMapping("/app-logs")
    public ResponseEntity<RestResponseBody<?>> appLog(HttpServletRequest req, Authentication authentication, @RequestBody List<@Valid PwsAppLogCrReqDTO> pwsAppLogCrReqDTOs) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        List<PwsAppLog> pwsAppLogs = new ArrayList<>();
        for (var pwsAppLogCrReqDTO : pwsAppLogCrReqDTOs) {
            var pwsAppLog = new PwsAppLog();
            BeanUtils.copyProperties(pwsAppLogCrReqDTO, pwsAppLog);
            pwsAppLogs.add(pwsAppLog);
        }

        var pwsAppLogsRsl = pwsAppLogService.addAll(pwsAppLogs);

        List<PwsAppLogResDTO> pwsAppLogResDTOs = new ArrayList<>();
        for (var pwsAppLog : pwsAppLogsRsl) {
            pwsAppLogResDTOs.add(modelMapper.map(pwsAppLog, PwsAppLogResDTO.class));
        }

        var restResponseBody = new RestResponseBody<>(200, pwsAppLogResDTOs);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PostMapping("/time-logs")
    public ResponseEntity<RestResponseBody<?>> timeLog(HttpServletRequest req, Authentication authentication, @RequestBody List<@Valid PwsTimeLogCrReqDTO> pwsTimeLogCrReqDTOs) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        List<PwsTimeLog> pwsTimeLogs = new ArrayList<>();
        for (var pwsTimeLogCrReqDTO : pwsTimeLogCrReqDTOs) {
            var pwsTimeLog = new PwsTimeLog();
            BeanUtils.copyProperties(pwsTimeLogCrReqDTO, pwsTimeLog);
            pwsTimeLogs.add(pwsTimeLog);
        }
        var pwsTimeLogsRsl = pwsTimeLogsService.addAll(pwsTimeLogs);

        List<PwsTimeLogResDTO> pwsTimeLogResDTOs = new ArrayList<>();
        for (var pwsTimeLog : pwsTimeLogsRsl) {
            pwsTimeLogResDTOs.add(modelMapper.map(pwsTimeLog, PwsTimeLogResDTO.class));
        }

        var restResponseBody = new RestResponseBody<>(200, pwsTimeLogResDTOs);
        restResponseBody.setPath(req.getRequestURI());

        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PostMapping("/url-logs")
    public ResponseEntity<RestResponseBody<?>> urlLog(HttpServletRequest req, Authentication authentication, @RequestBody List<@Valid PwsUrlLogCrReqDTO> pwsUrlLogCrReqDTOs) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        List<PwsUrlLog> pwsUrlLogs = new ArrayList<>();
        for (var pwsUrlLogCrReqDTO : pwsUrlLogCrReqDTOs) {
            var pwsUrlLog = new PwsUrlLog();
            BeanUtils.copyProperties(pwsUrlLogCrReqDTO, pwsUrlLog);
            pwsUrlLogs.add(pwsUrlLog);
        }

        var pwsUrlLogsRsl = pwsUrlLogService.addAll(pwsUrlLogs);

        List<PwsUrlLogResDTO> pwsUrlLogResDTOs = new ArrayList<>();
        pwsUrlLogsRsl.forEach(pwsUrlLog -> pwsUrlLogResDTOs.add(modelMapper.map(pwsUrlLog, PwsUrlLogResDTO.class)));

        var restResponseBody = new RestResponseBody<>(200, pwsUrlLogResDTOs);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @PostMapping("/screenshot-logs")
    public ResponseEntity<RestResponseBody<?>> screenshot(HttpServletRequest req, Authentication authentication, @Valid @RequestBody PwsScreenshotLogCrReqDTO pwsScreenshotLogCrReqDTO) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }

        var pwsScreenshot = new PwsScreenshot();
        BeanUtils.copyProperties(pwsScreenshotLogCrReqDTO, pwsScreenshot);

        pwsScreenshot.setFilePath(pwsScreenshotLogCrReqDTO.filename());

        var pwsScreenshotRsl = pwsScreenshotService.addAll(pwsScreenshot);
        var pwsScreenshotResDTO = modelMapper.map(pwsScreenshotRsl, PwsScreenshotResDTO.class);

        var restResponseBody = new RestResponseBody<>(200, pwsScreenshotResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }
}
