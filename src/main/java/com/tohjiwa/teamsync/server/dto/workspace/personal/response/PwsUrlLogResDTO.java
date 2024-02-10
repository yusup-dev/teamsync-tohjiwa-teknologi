package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
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
public class PwsUrlLogResDTO {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private Long pwsProjectId;
    private Long pwsTaskId;
    private Date sessionStart;
    private Date sessionEnd;
    private Long sessionTotalTime;
    private String name;
    private String process;
    private String windowTitle;
    private String url;
    private String domain;
    private ProductivityStatus productivityStatus;
    private Status status;
}
