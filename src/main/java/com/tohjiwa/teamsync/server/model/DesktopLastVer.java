package com.tohjiwa.teamsync.server.model;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DesktopLastVer {
    private Date date;
    private int major;
    private int minor;
    private int fix;
    private String downloadLink;
}
