package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Allow users to add tasks manually
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class TaskConfig {
    private String addTask;
}
