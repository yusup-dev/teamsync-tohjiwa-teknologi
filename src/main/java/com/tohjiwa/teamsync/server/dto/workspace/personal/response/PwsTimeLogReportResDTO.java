package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwsTimeLogReportResDTO {
    private int zoneOffsetHour;
    private Date dateStart;
    private Date dateEnd;
    private Long workingTime;
}
