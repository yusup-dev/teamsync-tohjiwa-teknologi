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
public class User {
    private Long id;
    private Date createdDate;
    private String username;
    private String email;
    private String phoneNumberCountryCode;
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
}
