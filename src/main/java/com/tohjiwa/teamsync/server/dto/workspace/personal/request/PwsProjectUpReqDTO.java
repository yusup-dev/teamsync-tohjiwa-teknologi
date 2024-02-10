package com.tohjiwa.teamsync.server.dto.workspace.personal.request;

import jakarta.validation.constraints.NotNull;

public record PwsProjectUpReqDTO(
        @NotNull Long id,
        String name,
        String description) {
}
