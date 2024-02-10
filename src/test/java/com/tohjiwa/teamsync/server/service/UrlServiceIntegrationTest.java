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
class UrlServiceIntegrationTest {
    @Autowired
    UrlService urlService;
    @Autowired
    PwsUserConfigRepository pwsUserConfigRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUrls_with_SpecificUser() {
        var urls = urlService.getUrls(-1L, -1L);
        Assertions.assertEquals(ProductivityStatus.PRODUCTIVE, urls.get(0).getProductivityStatus());
        Assertions.assertEquals("", urls.get(0).getUrl());
        Assertions.assertEquals("facebook.com", urls.get(0).getDomain());
        Assertions.assertEquals("Facebook", urls.get(0).getName());
        Assertions.assertEquals(1L, urls.get(0).getUrlCategoryId());
    }

    @Test
    void getUrls_with_SpecificPws() {
        var config = pwsUserConfigRepository.findByUser_IdAndPws_Id(-1L, -1L);
        if(config.isPresent()) {
            config.get().getUserConfiguration().setSpecificUrl(false);
            pwsUserConfigRepository.save(config.get());
        }

        var urls = urlService.getUrls(-1L, -1L);
        Assertions.assertEquals(ProductivityStatus.UNPRODUCTIVE, urls.get(0).getProductivityStatus());
        Assertions.assertEquals("", urls.get(0).getUrl());
        Assertions.assertEquals("facebook.com", urls.get(0).getDomain());
        Assertions.assertEquals("Facebook", urls.get(0).getName());
        Assertions.assertEquals(1L, urls.get(0).getUrlCategoryId());
    }
}