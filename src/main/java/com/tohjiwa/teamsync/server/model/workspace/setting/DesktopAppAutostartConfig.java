package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Configure if desktop application should force start when employee computer turned on
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class DesktopAppAutostartConfig {
    private String desktopAppAutostart;
}
