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
public class App {
    private Long id;
    private Date createdDate;
    private String name;
    private String process;
    private String term;
    private Long appCategoryId;
    private ProductivityStatus productivityStatus;
}
