package com.tohjiwa.teamsync.server.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserResDTO {
    private Long id;
    private Date createdDate;
    private String username;
    private String email;
    private String phoneNumberCountryCode;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
