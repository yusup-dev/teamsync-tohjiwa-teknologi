package com.tohjiwa.teamsync.server.dto.workspace.personal.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Map;

public record PwsActivityLogCrReqDTO(
        @NotNull Date createdDate,
        @NotNull Long userId,
        @NotNull Long pwsId,
        @NotNull Long pwsProjectId,
        @NotNull Long pwsTaskId,
        @NotNull Date sessionStart,
        @NotNull Date sessionEnd,
        @NotNull Long sessionTotalTime,
        @NotNull byte pwsActivityId,
        @NotNull Map<String, Object> state) {
}
