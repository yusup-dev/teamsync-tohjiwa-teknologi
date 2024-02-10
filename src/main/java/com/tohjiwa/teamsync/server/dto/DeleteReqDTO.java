package com.tohjiwa.teamsync.server.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteReqDTO(@NotNull Long id) {
}
