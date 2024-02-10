package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwsProjectSyncResDTO {
    @NotNull private List<PwsProjectSyncDtResDTO> ps;
    @NotNull private List<PwsTaskSyncDtResDTO> ts;
}
