package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

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
public class PwsActivityLogResDTO {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private Long pwsProjectId;
    private Long pwsTaskId;
    private Date sessionStart;
    private Date sessionEnd;
    private Long sessionTotalTime;
    private Long pwsActivityId;
    private Map<String, Object> state;
    private Status status;
}
