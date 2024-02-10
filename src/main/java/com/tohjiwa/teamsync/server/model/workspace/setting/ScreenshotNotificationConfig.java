package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Specify if the application should notify employees when screenshots are taken
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ScreenshotNotificationConfig {
    private String notify;
    private String playSound;
    private String appClientSetting;
}
