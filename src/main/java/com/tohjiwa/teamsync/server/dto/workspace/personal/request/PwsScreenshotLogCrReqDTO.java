package com.tohjiwa.teamsync.server.dto.workspace.personal.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PwsScreenshotLogCrReqDTO(
        @NotNull Date createdDate,
        @NotNull Long userId,
        @NotNull Long pwsId,
        @NotNull Long pwsProjectId,
        @NotNull Long pwsTaskId,
        @NotNull String filename) {
}
