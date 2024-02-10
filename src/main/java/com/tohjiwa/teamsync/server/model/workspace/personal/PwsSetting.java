package com.tohjiwa.teamsync.server.model.workspace.personal;

import com.tohjiwa.teamsync.server.model.workspace.setting.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PwsSetting {
    private Long pwsId;
    private ScreenshotConfig screenshotConfig;
    private ActivityTrackingConfig activityTrackingConfig;
    private AppTrackingConfig appTrackingConfig;
    private UrlTrackingConfig urlTrackingConfig;
    private AddOfflineTimeConfig addOfflineTimeConfig;
    private RemoveTimeConfig removeTimeConfig;
    private IdleTimeConfig idleTimeConfig;
    private ScreenshotNotificationConfig screenshotNotificationConfig;
    private DesktopAppAutostartConfig desktopAppAutostartConfig;
    private TaskConfig taskConfig;
    private IPAddressConfig ipAddressConfig;
    private TimesSheetsConfig timesSheetsConfig;
}
