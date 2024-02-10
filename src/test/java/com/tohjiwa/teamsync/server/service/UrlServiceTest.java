package com.tohjiwa.teamsync.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUrlRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserUrlRepository;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUrlEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserUrlEntity;
import com.tohjiwa.teamsync.server.model.Url;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UrlServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private PwsUrlRepository pwsUrlRepository;
    @Mock
    private PwsUserUrlRepository pwsUserUrlRepository;
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private UrlService urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock data for user
        var pwsUserConfig = PwsUserConfig.builder()
                .specificUrl(true)
                .specificApp(true)
                .specificPwsSetting(true)
                .build();
        Mockito.when(userService.getPwsUserConfig(-1L, -1L)).thenReturn(pwsUserConfig);

        // Mock data for general workspace
        List<Url> pwsUrls = new ArrayList<>();
        var pwsUrl = Url.builder()
                .id(1L)
                .urlCategoryId(1L)
                .url("https://google.com")
                .domain("google.com")
                .name("google")
                .productivityStatus(ProductivityStatus.UNPRODUCTIVE)
                .build();
        pwsUrls.add(pwsUrl);
        var pwsUrlEntity = PwsUrlEntity.builder()
                .id(-1L)
                .pws(PwsEntity.builder().id(-1L).build())
                .urls(pwsUrls)
                .build();
        Mockito.when(pwsUrlRepository.findByPws_Id(-1L)).thenReturn(Optional.of(pwsUrlEntity));

        // Mock data specific user url
        List<Url> userUrls = new ArrayList<>();
        var userUrl = Url.builder()
                .id(1L)
                .urlCategoryId(1L)
                .url("https://google.com")
                .domain("google.com")
                .name("google")
                .productivityStatus(ProductivityStatus.PRODUCTIVE)
                .build();
        userUrls.add(userUrl);
        var pwsUserUrlEntity = PwsUserUrlEntity.builder()
                .user(UserEntity.builder().id(-1L).build())
                .pws(PwsEntity.builder().id(-1L).build())
                .urls(userUrls)
                .build();
        Mockito.when(pwsUserUrlRepository.findByUser_IdAndPws_Id(-1L, -1L)).thenReturn(Optional.of(pwsUserUrlEntity));
    }

    @Test
    void getUrls_with_SpecificUser() {
        var urls = urlService.getUrls(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.PRODUCTIVE, urls.get(0).getProductivityStatus());
        Assertions.assertEquals("https://google.com", urls.get(0).getUrl());
        Assertions.assertEquals("google.com", urls.get(0).getDomain());
        Assertions.assertEquals("google", urls.get(0).getName());
        Assertions.assertEquals(1L, urls.get(0).getUrlCategoryId());
    }

    @Test
    void getUrls_with_SpecificUserButNotAvailable() {
        Mockito.when(pwsUserUrlRepository.findByUser_IdAndPws_Id(-1L, -1L)).thenReturn(Optional.empty());

        var urls = urlService.getUrls(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.UNPRODUCTIVE, urls.get(0).getProductivityStatus());
        Assertions.assertEquals("https://google.com", urls.get(0).getUrl());
        Assertions.assertEquals("google.com", urls.get(0).getDomain());
        Assertions.assertEquals("google", urls.get(0).getName());
        Assertions.assertEquals(1L, urls.get(0).getUrlCategoryId());
    }

    @Test
    void getUrls_with_SpecificPws() {
        // Mock data for user
        var pwsUserConfig = PwsUserConfig.builder()
                .specificUrl(false)
                .specificApp(true)
                .specificPwsSetting(true)
                .build();
        Mockito.when(userService.getPwsUserConfig(-1L, -1L)).thenReturn(pwsUserConfig);

        var urls = urlService.getUrls(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.UNPRODUCTIVE, urls.get(0).getProductivityStatus());
        Assertions.assertEquals("https://google.com", urls.get(0).getUrl());
        Assertions.assertEquals("google.com", urls.get(0).getDomain());
        Assertions.assertEquals("google", urls.get(0).getName());
        Assertions.assertEquals(1L, urls.get(0).getUrlCategoryId());
    }

    @Test
    void getUrls_with_SpecificPwsDefault() {
        // Mock data for user
        var pwsUserConfig = PwsUserConfig.builder()
                .specificUrl(false)
                .specificApp(true)
                .specificPwsSetting(true)
                .build();
        Mockito.when(userService.getPwsUserConfig(-1L, -1L)).thenReturn(pwsUserConfig);

        Mockito.when(pwsUrlRepository.findByPws_Id(-1L)).thenReturn(Optional.empty());

        var urls = urlService.getUrls(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.UNPRODUCTIVE, urls.get(0).getProductivityStatus());
        Assertions.assertEquals("", urls.get(0).getUrl());
        Assertions.assertEquals("facebook.com", urls.get(0).getDomain());
        Assertions.assertEquals("Facebook", urls.get(0).getName());
        Assertions.assertEquals(1L, urls.get(0).getUrlCategoryId());
    }
}