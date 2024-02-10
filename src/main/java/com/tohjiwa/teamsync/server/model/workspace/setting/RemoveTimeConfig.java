package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Allow user to remove worked time
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class RemoveTimeConfig {
    private String removeTime;
}
