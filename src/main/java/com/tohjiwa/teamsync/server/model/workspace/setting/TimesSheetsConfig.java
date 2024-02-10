package com.tohjiwa.teamsync.server.model.workspace.setting;

import lombok.*;

/*
Specify if users should be able to view timesheets page
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class TimesSheetsConfig {
    private String viewTimesSheets;
}
