package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Track what websites your team members visit
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UrlTrackingConfig {
    private String urlTracking;
}
