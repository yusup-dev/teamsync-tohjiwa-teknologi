package com.tohjiwa.teamsync.server.dto.workspace.personal.request;

import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PwsAppLogCrReqDTO(
        @NotNull Date createdDate,
        @NotNull Long userId,
        @NotNull Long pwsId,
        @NotNull Long pwsProjectId,
        @NotNull Long pwsTaskId,
        @NotNull Date sessionStart,
        @NotNull Date sessionEnd,
        @NotNull Long sessionTotalTime,
        @NotNull String name,
        @NotNull String process,
        @NotNull String windowTitle,
        @NotNull ProductivityStatus productivityStatus) {
}
