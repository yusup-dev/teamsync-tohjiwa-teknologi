package com.tohjiwa.teamsync.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsAppRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserAppRepository;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsAppEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserAppEntity;
import com.tohjiwa.teamsync.server.model.App;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AppServiceTest {
    @Mock
    private PwsAppRepository pwsAppRepository;
    @Mock
    private PwsUserAppRepository pwsUserAppRepository;
    @Mock
    private UserService userService;
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    AppService appService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var pwsUserConfig = PwsUserConfig.builder()
                .specificUrl(true)
                .specificApp(true)
                .specificPwsSetting(true)
                .build();
        Mockito.when(userService.getPwsUserConfig(-1L, -1L)).thenReturn(pwsUserConfig);

        // Mock data for general workspace
        List<App> pwsApps = new ArrayList<>();
        var pwsApp = App.builder()
                .id(1L)
                .name("Microsoft Edge")
                .term("edge")
                .process("edge.exe")
                .appCategoryId(1L)
                .productivityStatus(ProductivityStatus.PRODUCTIVE)
                .build();
        pwsApps.add(pwsApp);
        var pwsAppEntity = PwsAppEntity.builder()
                .id(1L)
                .pws(PwsEntity.builder().id(1L).build())
                .apps(pwsApps)
                .build();
        Mockito.when(pwsAppRepository.findByPws_Id(-1L)).thenReturn(Optional.of(pwsAppEntity));

        // Mock data specific user apps
        List<App> userApps = new ArrayList<>();
        var userApp = App.builder()
                .id(1L)
                .name("Microsoft Edge")
                .term("edge")
                .process("edge.exe")
                .appCategoryId(1L)
                .productivityStatus(ProductivityStatus.UNPRODUCTIVE)
                .build();
        userApps.add(userApp);
        var pwsUserAppEntity = PwsUserAppEntity.builder()
                .id(1L)
                .pws(PwsEntity.builder().id(1L).build())
                .apps(userApps)
                .build();
        Mockito.when(pwsUserAppRepository.findByUser_IdAndPws_Id(-1L, -1L)).thenReturn(Optional.of(pwsUserAppEntity));
    }

    @Test
    void getApps_with_SpecificUser() {
        var apps = appService.getApps(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.UNPRODUCTIVE, apps.get(0).getProductivityStatus());
        Assertions.assertEquals("edge", apps.get(0).getTerm());
        Assertions.assertEquals("edge.exe", apps.get(0).getProcess());
        Assertions.assertEquals("Microsoft Edge", apps.get(0).getName());
        Assertions.assertEquals(1L, apps.get(0).getAppCategoryId());
    }

    @Test
    void getApps_with_SpecificUserButNotAvailable() {
        Mockito.when(pwsUserAppRepository.findByUser_IdAndPws_Id(-1L, -1L)).thenReturn(Optional.empty());

        var apps = appService.getApps(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.PRODUCTIVE, apps.get(0).getProductivityStatus());
        Assertions.assertEquals("edge", apps.get(0).getTerm());
        Assertions.assertEquals("edge.exe", apps.get(0).getProcess());
        Assertions.assertEquals("Microsoft Edge", apps.get(0).getName());
        Assertions.assertEquals(1L, apps.get(0).getAppCategoryId());
    }

    @Test
    void getApps_with_SpecificPws() {
        // Mock data for user
        var pwsUserConfig = PwsUserConfig.builder()
                .specificUrl(true)
                .specificApp(false)
                .specificPwsSetting(true)
                .build();
        Mockito.when(userService.getPwsUserConfig(-1L, -1L)).thenReturn(pwsUserConfig);

        var apps = appService.getApps(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.PRODUCTIVE, apps.get(0).getProductivityStatus());
        Assertions.assertEquals("edge", apps.get(0).getTerm());
        Assertions.assertEquals("edge.exe", apps.get(0).getProcess());
        Assertions.assertEquals("Microsoft Edge", apps.get(0).getName());
        Assertions.assertEquals(1L, apps.get(0).getAppCategoryId());
    }

    @Test
    void getApps_with_SpecificPwsDefault() {
        // Mock data for user
        var pwsUserConfig = PwsUserConfig.builder()
                .specificUrl(true)
                .specificApp(false)
                .specificPwsSetting(true)
                .build();
        Mockito.when(userService.getPwsUserConfig(-1L, -1L)).thenReturn(pwsUserConfig);

        Mockito.when(pwsAppRepository.findByPws_Id(-1L)).thenReturn(Optional.empty());

        var apps = appService.getApps(-1L, -1L);

        Assertions.assertEquals(ProductivityStatus.PRODUCTIVE, apps.get(0).getProductivityStatus());
        Assertions.assertEquals("msedge.exe, mikocok", apps.get(0).getTerm());
        Assertions.assertEquals("msedge", apps.get(0).getProcess());
        Assertions.assertEquals("Microsoft Edge", apps.get(0).getName());
        Assertions.assertEquals(1L, apps.get(0).getAppCategoryId());
    }
}