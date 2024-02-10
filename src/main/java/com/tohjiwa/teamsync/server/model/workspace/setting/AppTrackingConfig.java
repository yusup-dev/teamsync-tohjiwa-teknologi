package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Track what applications your team members use
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AppTrackingConfig {
    private String applicationTracking;
}
