package com.tohjiwa.teamsync.server.model.workspace;

import java.util.Date;

public record TimeLogReport(
        int zoneOffsetHour,
        Date dateStart,
        Date dateEnd,
        Long workingTime) {
}
