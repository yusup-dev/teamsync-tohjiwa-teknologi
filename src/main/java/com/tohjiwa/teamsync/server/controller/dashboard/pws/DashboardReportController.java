package com.tohjiwa.teamsync.server.controller.dashboard.pws;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dto.workspace.personal.response.PwsTimeLogReportResDTO;
import com.tohjiwa.teamsync.server.model.RestResponseBody;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsAppLogRepository;
import com.tohjiwa.teamsync.server.model.SecurityUser;
import com.tohjiwa.teamsync.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;

@Service("Dashboard-Report")
@Controller
@RequestMapping("/dashboard")
public class DashboardReportController {

    @Autowired
    UserService userService;
    @Autowired
    PwsAppLogRepository appLogRepository;

    @GetMapping("/pws/{pwsId}/report/report-1")
    public String report1(Model model, Authentication authentication, @PathVariable Long pwsId) {
        var jwt = (SecurityUser) authentication.getPrincipal();
        Long userId = jwt.user().getId();

        var userRsl = userService.getUser(userId);

        model.addAttribute("user", userRsl.get());
        return "dashboard/pws/report/report-1";
    }

    @GetMapping("/app")
    public ResponseEntity<RestResponseBody<PwsTimeLogReportResDTO>> hash(HttpServletRequest req, @NotNull @RequestParam int offset) {
        var userId = 1L;
        var pwsId = 1L;

        LocalDate date = LocalDate.ofInstant(Instant.now(), ZoneOffset.ofHours(offset));
        TemporalField dowField = WeekFields.ISO.dayOfWeek();
        var minDate = date.with(dowField, dowField.range().getMinimum());
        var maxDate = date.with(dowField, dowField.range().getMaximum());

        int currentWeek = date.get(WeekFields.ISO.weekOfMonth());

        System.out.println("current week : " + currentWeek);

        var localDateTimeMin = minDate.atTime(0, 0, 0);
        var localDateTimeMax = maxDate.atTime(23, 59, 59);

        var dateStart = Date.from(localDateTimeMin.toInstant(ZoneOffset.ofHours(offset)));
        var dateEnd = Date.from(localDateTimeMax.toInstant(ZoneOffset.ofHours(offset)));

        var workingTime = appLogRepository.sum(userId, pwsId, Status.ACTIVE, dateStart, dateEnd);

        for (var a : workingTime) {
            var d = Duration.ofMillis(a.getSessionTotalTime());
            var h = d.toHours();
            var m = d.toMinutes() - (60 * h);

            var z = h + "h" + " " + m + "m";

            var x = a.getName() + " " + a.getProcess() + " " + a.getProductivityStatus() + " " + z;
            System.out.println(x);
        }

        var res = new PwsTimeLogReportResDTO(
                0,
                dateStart,
                dateEnd,
                0L
        );

        var response = new RestResponseBody<>(200, res);
        response.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
