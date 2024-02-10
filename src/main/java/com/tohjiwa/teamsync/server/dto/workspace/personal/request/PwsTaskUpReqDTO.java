package com.tohjiwa.teamsync.server.dto.workspace.personal.request;

import jakarta.validation.constraints.NotNull;

public record PwsTaskUpReqDTO(
        @NotNull Long id,
        String name,
        String description) {
}
