package com.tohjiwa.teamsync.server.dto.workspace.personal.response;

import com.tohjiwa.teamsync.server.constant.Status;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PwsScreenshotResDTO {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private Long pwsProjectId;
    private Long pwsTaskId;
    private String filePath;
    private Status status;
}
