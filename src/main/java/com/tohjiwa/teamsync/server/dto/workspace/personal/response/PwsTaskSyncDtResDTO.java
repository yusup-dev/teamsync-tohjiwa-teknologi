package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwsTaskSyncDtResDTO extends PwsTaskResDTO {
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private boolean isDeleted;
    private Date deletedAt;
}
