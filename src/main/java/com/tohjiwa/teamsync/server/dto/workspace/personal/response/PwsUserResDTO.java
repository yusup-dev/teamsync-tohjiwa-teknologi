package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwsUserResDTO {
    private Long userId;
    private Long pwsId;
    private String pwsName;
    private String role;
}
