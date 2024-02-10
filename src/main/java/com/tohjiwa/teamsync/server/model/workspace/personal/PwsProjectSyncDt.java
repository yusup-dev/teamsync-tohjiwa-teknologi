package com.tohjiwa.teamsync.server.model.workspace.personal;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwsProjectSyncDt extends PwsProject{
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private boolean isDeleted;
    private Date deletedAt;
}
