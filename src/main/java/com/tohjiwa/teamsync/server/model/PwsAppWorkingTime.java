package com.tohjiwa.teamsync.server.model;

import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PwsAppWorkingTime {
    private Long pwsId;
    private Long userId;
    private String name;
    private String process;
    private ProductivityStatus productivityStatus;
    private Long sessionTotalTime;
}
