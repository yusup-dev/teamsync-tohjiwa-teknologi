package com.tohjiwa.teamsync.server.model.workspace.personal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PwsUserConfig {
    private boolean specificApp;
    private boolean specificUrl;
    private boolean specificPwsSetting;
}
