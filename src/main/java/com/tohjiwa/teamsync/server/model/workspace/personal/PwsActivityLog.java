package com.tohjiwa.teamsync.server.model.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import lombok.*;

import java.util.Date;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PwsActivityLog {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private Long pwsProjectId;
    private Long pwsTaskId;
    private Date sessionStart;
    private Date sessionEnd;
    private Long sessionTotalTime;
    private byte pwsActivityId;
    private Map<String, Object> state;
    private Status status;
}
