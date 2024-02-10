package com.tohjiwa.teamsync.server.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DesktopLastVerResDTO {
    private Date date;
    private int major;
    private int minor;
    private int fix;
    private String downloadLink;
}
