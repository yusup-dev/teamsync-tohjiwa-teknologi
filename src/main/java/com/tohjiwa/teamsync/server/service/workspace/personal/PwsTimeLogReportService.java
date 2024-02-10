package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsTimeLogRepository;
import com.tohjiwa.teamsync.server.model.workspace.TimeLogReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;

@Service
public class PwsTimeLogReportService {
    @Autowired
    PwsTimeLogRepository pwsTimeLogRepository;

    public TimeLogReport summaryWeekly(Long userId, Long pwsId, int offset) {
        LocalDate localDateNow = LocalDate.ofInstant(Instant.now(), ZoneOffset.ofHours(offset));
        TemporalField dowField = WeekFields.ISO.dayOfWeek();
        var minDate = localDateNow.with(dowField, dowField.range().getMinimum());
        var maxDate = localDateNow.with(dowField, dowField.range().getMaximum());

        var localDateTimeMin = minDate.atTime(0, 0, 0);
        var localDateTimeMax = maxDate.atTime(23, 59, 59);

        var dateStart = Date.from(localDateTimeMin.toInstant(ZoneOffset.ofHours(offset)));
        var dateEnd = Date.from(localDateTimeMax.toInstant(ZoneOffset.ofHours(offset)));

        var workingTime = pwsTimeLogRepository.sumTotalTimeBetween(userId, pwsId, Status.ACTIVE, dateStart, dateEnd);
        if (workingTime == null) {
            workingTime = 0L;
        }

        return new TimeLogReport(offset, dateStart, dateEnd, workingTime);
    }

    public TimeLogReport summaryBetween(Long userId, Long pwsId, LocalDate start, LocalDate end, int offset) {
        var localDateTimeMin = start.atTime(0, 0, 0);
        var localDateTimeMax = end.atTime(23, 59, 59);

        var dateStartR = Date.from(localDateTimeMin.toInstant(ZoneOffset.ofHours(offset)));
        var dateEndR = Date.from(localDateTimeMax.toInstant(ZoneOffset.ofHours(offset)));

        var workingTime = pwsTimeLogRepository.sumTotalTimeBetween(userId, pwsId, Status.ACTIVE, dateStartR, dateEndR);
        if (workingTime == null) {
            workingTime = 0L;
        }

        return new TimeLogReport(offset, dateStartR, dateEndR, workingTime);
    }

    public TimeLogReport summaryDaily(Long userId, Long pwsId, LocalDate day, int offset) {
        var localDateTimeMin = day.atTime(0, 0, 0);
        var localDateTimeMax = day.atTime(23, 59, 59);

        var dateStartR = Date.from(localDateTimeMin.toInstant(ZoneOffset.ofHours(offset)));
        var dateEndR = Date.from(localDateTimeMax.toInstant(ZoneOffset.ofHours(offset)));

        var workingTime = pwsTimeLogRepository.sumTotalTimeBetween(userId, pwsId, Status.ACTIVE, dateStartR, dateEndR);
        if (workingTime == null) {
            workingTime = 0L;
        }

        return new TimeLogReport(offset, dateStartR, dateEndR, workingTime);
    }
}
