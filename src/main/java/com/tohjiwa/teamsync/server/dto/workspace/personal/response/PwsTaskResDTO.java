package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import com.tohjiwa.teamsync.server.constant.LastOperation;
import com.tohjiwa.teamsync.server.constant.Source;
import com.tohjiwa.teamsync.server.constant.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwsTaskResDTO {
    @NotNull
    private Long id;
    @NotNull
    private Date createdDate;
    @NotNull
    private Long userId;
    @NotNull
    private Long pwsId;
    @NotNull
    private Long pwsProjectId;
    @NotNull
    private String name;
    private String description;
    private Long reporterUserId;
    private Long assigneeUserId;
    private Date deadline;
    private List<Long> tagIds;
    @NotNull
    private boolean isFlagged = false;
    @NotNull
    private LastOperation lastOperation;
    private Source source;
    @NotNull
    private Status status;
}
