package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Take Screenshot of user screen
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ScreenshotConfig {
    private int screenshotPerHour;
    private String autoBlur;
}
