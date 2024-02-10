package com.tohjiwa.teamsync.server.dto.workspace.personal.request;

import com.tohjiwa.teamsync.server.constant.Source;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PwsProjectCrReqDTO(
        @NotNull Long userId,
        @NotNull Long pwsId,
        @NotNull String name,
        String description,
        @NotNull Source source,
        List<Long> tagIds) {
}
