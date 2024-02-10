package com.tohjiwa.teamsync.server.model.workspace.personal;

import com.tohjiwa.teamsync.server.constant.LastOperation;
import com.tohjiwa.teamsync.server.constant.Source;
import com.tohjiwa.teamsync.server.constant.Status;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PwsProject {
    private Long id;
    private Date createdDate;
    private Long userId;
    private Long pwsId;
    private String name;
    private String description;
    private Source source;
    private List<Long> tagIds;
    private LastOperation lastOperation;
    private Status status;
}
