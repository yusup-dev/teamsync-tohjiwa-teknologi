package com.tohjiwa.teamsync.server.controller.api.client.desktop;

import com.tohjiwa.teamsync.server.dto.workspace.personal.response.PwsTimeLogReportResDTO;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.service.workspace.personal.PwsTimeLogReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Service("API-Client-Desktop-Report")
@RestController
@RequestMapping("/api/client/desktop/v1/report")
public class ReportController {
    @Autowired
    PwsTimeLogReportService pwsTimeLogReportService;

    @GetMapping("time-logs/weekly")
    public ResponseEntity<RestResponseBody<?>> getTimeLogsWeekly(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam int offset, @NotNull @RequestParam Long pwsId) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var timeLogReport = pwsTimeLogReportService.summaryWeekly(userId, pwsId, offset);

        var pwsTimeLogReportResDTO = new PwsTimeLogReportResDTO();
        BeanUtils.copyProperties(timeLogReport, pwsTimeLogReportResDTO);

        var restResponseBody = new RestResponseBody<>(200, pwsTimeLogReportResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @GetMapping("time-logs/between")
    public ResponseEntity<RestResponseBody<?>> getTimeLogsBetween(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam String dateStart, @NotNull @RequestParam String dateEnd, @NotNull @RequestParam int offset, @NotNull @RequestParam Long pwsId) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var minDate = LocalDate.parse(dateStart);
        var maxDate = LocalDate.parse(dateEnd);

        var timeLogReport = pwsTimeLogReportService.summaryBetween(userId, pwsId, minDate, maxDate, offset);

        var pwsTimeLogReportResDTO = new PwsTimeLogReportResDTO();
        BeanUtils.copyProperties(timeLogReport, pwsTimeLogReportResDTO);

        var restResponseBody = new RestResponseBody<>(200, pwsTimeLogReportResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }

    @GetMapping("time-logs/daily")
    public ResponseEntity<RestResponseBody<?>> getTimeLogsDaily(HttpServletRequest req, Authentication authentication, @NotNull @RequestParam String day, @NotNull @RequestParam int offset, @NotNull @RequestParam Long pwsId) {
        if (authentication == null) {
            throw new RuntimeException("auth null");
        }
        var jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("userId");

        var localDay = LocalDate.parse(day);

        var timeLogReport = pwsTimeLogReportService.summaryDaily(userId, pwsId, localDay, offset);

        var pwsTimeLogReportResDTO = new PwsTimeLogReportResDTO();
        BeanUtils.copyProperties(timeLogReport, pwsTimeLogReportResDTO);

        var restResponseBody = new RestResponseBody<>(200, pwsTimeLogReportResDTO);
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.OK);
    }
}
