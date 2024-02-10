package com.tohjiwa.teamsync.server.dto;

public record UserUpdateReqDTO(
        String phoneNumberCountryCode,
        String phoneNumber,
        String firstName,
        String lastName,
        String skills,
        String designation,
        String url,
        String country,
        String city,
        String zipCode,
        String bio,
        String socialUrlLinkedin,
        String socialUrlGithub,
        String socialUrlPinterest,
        String socialUrlDribble,
        String socialUrlTwitter,
        String socialUrlFacebook,
        String socialUrlInstagram
) {
}
