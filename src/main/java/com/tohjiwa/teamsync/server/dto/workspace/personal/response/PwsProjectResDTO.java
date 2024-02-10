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
public class PwsProjectResDTO {
    @NotNull
    private Long id;
    @NotNull
    private Date createdDate;
    @NotNull
    private Long userId;
    @NotNull
    private Long pwsId;
    @NotNull
    private String name;
    private String description;
    private List<Long> tagIds;
    @NotNull
    private LastOperation lastOperation;
    private Source source;
    @NotNull
    private Status status;
}
