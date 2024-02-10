package com.tohjiwa.teamsync.server.model.workspace.personal;

import com.tohjiwa.teamsync.server.constant.LastOperation;
import com.tohjiwa.teamsync.server.constant.Source;
import com.tohjiwa.teamsync.server.constant.Status;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PwsTask {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private Long pwsProjectId;
    private String name;
    private String description;
    private Long reporterUserId;
    private Long assigneeUserId;
    private Date deadline;
    private List<Long> tagIds;
    private boolean isFlagged;
    private LastOperation lastOperation;
    private Source source;
    private Status status;
}
