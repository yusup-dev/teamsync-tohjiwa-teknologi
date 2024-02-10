package com.tohjiwa.teamsync.server.model.workspace.personal;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwsUser {
    private Long userId;
    private Long pwsId;
    private String pwsName;
    private String role;
}
