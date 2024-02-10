package com.tohjiwa.teamsync.server.dto.workspace.personal.request;

import com.tohjiwa.teamsync.server.constant.Source;
import jakarta.validation.constraints.NotNull;

public record PwsTaskCrReqDTO(
        @NotNull Long userId,
        @NotNull Long pwsId,
        @NotNull Long pwsProjectId,
        @NotNull String name,
        @NotNull String description,
        @NotNull Source source) {
}
