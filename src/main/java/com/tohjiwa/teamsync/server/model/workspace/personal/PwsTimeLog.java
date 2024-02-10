package com.tohjiwa.teamsync.server.model.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PwsTimeLog {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private Long pwsProjectId;
    private Long pwsTaskId;
    private Date sessionStart;
    private Date sessionEnd;
    private long sessionTotalTime;
    private Status status;
}
