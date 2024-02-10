package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import com.tohjiwa.teamsync.server.constant.Status;
import lombok.Data;

import java.util.Date;

@Data
public class PwsTimeLogResDTO {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private Long pwsProjectId;
    private Long pwsTaskId;
    private Date sessionStart;
    private Date sessionEnd;
    private Long sessionTotalTime;
    private Status status;
}
