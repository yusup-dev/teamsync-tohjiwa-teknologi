package com.tohjiwa.teamsync.server.service;

import com.tohjiwa.teamsync.server.controller.api.client.desktop.VersionController;
import com.tohjiwa.teamsync.server.model.DesktopLastVer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class VersionService {

    public DesktopLastVer getDesktopLastVer() throws Exception {
        ClassLoader classLoader = VersionController.class.getClassLoader();

        Properties applicationProps = new Properties();
        var file = classLoader.getResourceAsStream("application.properties");
        applicationProps.load(file);

        var desktopLastVer = new DesktopLastVer();
        desktopLastVer.setDate(new Date(Long.parseLong(applicationProps.getProperty("client.version.date"))));
        desktopLastVer.setMajor(Integer.parseInt(applicationProps.getProperty("client.version.major")));
        desktopLastVer.setMinor(Integer.parseInt(applicationProps.getProperty("client.version.minor")));
        desktopLastVer.setFix(Integer.parseInt(applicationProps.getProperty("client.version.fix")));
        desktopLastVer.setDownloadLink(applicationProps.getProperty("client.version.download-link"));

        return desktopLastVer;
    }
}
