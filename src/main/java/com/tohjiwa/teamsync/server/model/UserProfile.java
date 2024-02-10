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
public class UserProfile {
    private Long userId;
    private Date createdDate;
    private String username;
    private String email;
    private String phoneNumberCountryCode;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String skills;
    private String company;
    private String designation;
    private String url;
    private String country;
    private String city;
    private String zipCode;
    private String bio;
    private String socialUrlLinkedin;
    private String socialUrlGithub;
    private String socialUrlPinterest;
    private String socialUrlDribble;
    private String socialUrlTwitter;
    private String socialUrlFacebook;
    private String socialUrlInstagram;
}
