package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Allow the user not to delete time after downtime. Downtime The period during which the user monitored his time but was inactive
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class IdleTimeConfig {
    private String idleTime;
    private int deleteAfterMinutes;
}
