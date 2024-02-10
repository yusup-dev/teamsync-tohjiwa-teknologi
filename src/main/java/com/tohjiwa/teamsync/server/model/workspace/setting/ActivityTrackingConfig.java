package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Track mouse and keyboard Activity Level
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ActivityTrackingConfig {
    private String activityTracking;
}
