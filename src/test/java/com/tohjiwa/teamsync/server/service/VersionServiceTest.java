package com.tohjiwa.teamsync.server.service;

import com.tohjiwa.teamsync.server.model.DesktopLastVer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionServiceTest {

    private VersionService versionService;

    @BeforeEach
    void setUp() {
        versionService = new VersionService();
    }

    @Test
    void getDesktopLastVer() throws Exception {
        var versionActual = versionService.getDesktopLastVer();
        var versionExpected = DesktopLastVer.builder()
                .date(new Date(1674900000000L))
                .major(1)
                .minor(1)
                .fix(0)
                .downloadLink("https://www.tohjiwa.com")
                .build();

        assertEquals(versionActual, versionExpected);
    }
}