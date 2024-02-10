package com.tohjiwa.teamsync.server.service;

import com.tohjiwa.teamsync.server.constant.ProductivityStatus;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserConfigRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"classpath:db/seed_data.sql", "classpath:db/dummy_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Tag("IntegrationTest")
class AppServiceIntegrationTest {
    @Autowired
    AppService appService;
    @Autowired
    PwsUserConfigRepository pwsUserConfigRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getApps_with_SpecificUser() {
        var config = pwsUserConfigRepository.findByUser_IdAndPws_Id(-1L, -1L);
        if(config.isPresent()) {
            config.get().getUserConfiguration().setSpecificApp(true);
            pwsUserConfigRepository.save(config.get());
        }

        var apps = appService.getApps(-1L, -1L);
        Assertions.assertEquals(ProductivityStatus.UNPRODUCTIVE, apps.get(0).getProductivityStatus());
        Assertions.assertEquals("msedge.exe, mikocok", apps.get(0).getTerm());
        Assertions.assertEquals("msedge", apps.get(0).getProcess());
        Assertions.assertEquals("Microsoft Edge", apps.get(0).getName());
        Assertions.assertEquals(1L, apps.get(0).getAppCategoryId());
    }

    @Test
    void getApps_with_SpecificPws() {
        var apps = appService.getApps(-1L, -1L);
        Assertions.assertEquals(ProductivityStatus.PRODUCTIVE, apps.get(0).getProductivityStatus());
        Assertions.assertEquals("msedge.exe, mikocok", apps.get(0).getTerm());
        Assertions.assertEquals("msedge", apps.get(0).getProcess());
        Assertions.assertEquals("Microsoft Edge", apps.get(0).getName());
        Assertions.assertEquals(1L, apps.get(0).getAppCategoryId());
    }
}