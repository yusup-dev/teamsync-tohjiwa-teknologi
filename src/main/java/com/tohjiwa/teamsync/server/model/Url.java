package com.tohjiwa.teamsync.server.model;

import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Url {
    private Long id;
    private Date createdDate;
    private String name;
    private String domain;
    private String url;
    private Long urlCategoryId;
    private ProductivityStatus productivityStatus;
}
