package com.tohjiwa.teamsync.server.service.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsTimeLog;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsTimeLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PwsTimeLogServiceTest {

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private PwsTimeLogRepository pwsTimeLogRepository;

    @InjectMocks
    private PwsTimeLogService pwsTimeLogsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Map<String, Object> headers = Map.ofEntries(
                Map.entry("userId", 1L)
        );
        Map<String, Object> claims = Map.ofEntries(
                Map.entry("userId", 1L)
        );
        Jwt jwt = new Jwt("test-token", Instant.now(), Instant.now().plus(1, ChronoUnit.DAYS), headers, claims);

        Authentication auth = new JwtAuthenticationToken(jwt);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void create() {

        Throwable exception = assertThrows(Exception.class, () -> {
            List<PwsTimeLog> pwsTimeLogs = new ArrayList<>();

            var a = PwsTimeLog.builder()
                    .pwsId(1L)
                    .pwsTaskId(1L)
                    .createdDate(Date.from(Instant.now()))
                    .status(Status.ACTIVE)
                    .pwsProjectId(1L)
                    .userId(1L)
                    .sessionStart(Date.from(Instant.now()))
                    .sessionEnd(Date.from(Instant.now().plus(3, ChronoUnit.MINUTES)))
                    .sessionTotalTime(10L)
                    .build();
            pwsTimeLogs.add(a);

            var pwsTimeLogRsl = pwsTimeLogsService.addAll(null);
        });

        assertEquals(NullPointerException.class, exception.getClass());
    }
}