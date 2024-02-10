package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Allow user to add time not tracked by the program to their timeline manually. It is often used to account for work away from a computer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddOfflineTimeConfig {
    String addOfflineTime;
}
