package com.tohjiwa.teamsync.server.service;

import com.tohjiwa.teamsync.server.model.User;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"classpath:db/seed_data.sql", "classpath:db/dummy_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Tag("IntegrationTest")
class UserServiceIntegrationTest {
    private static RedisServer redisServer;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Before("")

    @BeforeAll
    public static void startRedis() {
        redisServer = new RedisServer(6378);
        redisServer.start();
    }

    @AfterAll
    public static void stopRedis() {
        redisServer.stop();
    }

    @Test
    void getUser() {
        var userRsl = userService.getUser(-1L);
        Assertions.assertTrue(userRsl.isPresent());

        Assertions.assertEquals(-1L, userRsl.get().getId());
        Assertions.assertEquals("user@tmsync.co", userRsl.get().getEmail());
        Assertions.assertEquals("Eric", userRsl.get().getFirstName());
        Assertions.assertEquals("Sanjaya", userRsl.get().getLastName());
        Assertions.assertEquals("87860408682", userRsl.get().getPhoneNumber());
        Assertions.assertEquals("+62", userRsl.get().getPhoneNumberCountryCode());
        Assertions.assertEquals("ericsanjaya", userRsl.get().getUsername());
        Assertions.assertEquals("$2a$10$x7D7ZrUozIdeUvYT9./Vf.q.Vzqr4mEN7869O1wwqzBUdOn7JRQUu", userRsl.get().getPassword());
    }

    @Test
    void getUser_with_UserIdNotExist() {
        var userRsl = userService.getUser(-99L);
        Assertions.assertFalse(userRsl.isPresent());
    }

    @Test
    void getUserStatus() {
        var x = userService.getUserStatus(-1L);
    }

    @Test
    void register() throws Exception {
        var user = User.builder()
                .email("user1@tmsync.co")
                .firstName("First")
                .lastName("Last")
                .password("password")
                .build();
        var actual = userService.register(user);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals("user1@tmsync.co", actual.getEmail());
        Assertions.assertEquals("First", actual.getFirstName());
        Assertions.assertEquals("Last", actual.getLastName());
    }

    @Test
    void register_with_SameEmail() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .email("user@tmsync.co")
                    .firstName("First")
                    .lastName("Last")
                    .password("password")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("email have ben used in other account", exception.getMessage());
    }

    @Test
    void register_with_wrongEmailFormat() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .email("user_tmsync.co")
                    .firstName("First")
                    .lastName("Last")
                    .password("password")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("Please input valid email", exception.getMessage());
    }

    @Test
    void register_with_1CharFirstName() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .email("user@tmsync.co")
                    .firstName("F")
                    .lastName("Last")
                    .password("password")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("First name min is 2 and max is 45", exception.getMessage());
    }

    @Test
    void register_with_50CharFirstName() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .email("user@tmsync.co")
                    .firstName("First123456789101112131415161718192021222324252627")
                    .lastName("Last")
                    .password("password")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("First name min is 2 and max is 45", exception.getMessage());
    }

    @Test
    void register_with_50CharLastName() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .email("user@tmsync.co")
                    .firstName("First")
                    .lastName("Last12345678910111213141516171819202122232425262728")
                    .password("password")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("Last name min is 1 and max is 45", exception.getMessage());
    }

    @Test
    void register_with_1CharPassword() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .email("user@tmsync.co")
                    .firstName("First")
                    .lastName("Last")
                    .password("p")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("Password min 8 character and max 32 character", exception.getMessage());
    }

    @Test
    void register_with_50CharPassword() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .email("user@tmsync.co")
                    .firstName("First")
                    .lastName("Last")
                    .password("12345678901011121314151617181920212223242526272829")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("Password min 8 character and max 32 character", exception.getMessage());
    }

    @Test
    void getPwsUserConfig() {
        var actual = userService.getPwsUserConfig(-1L, -1L);
        var expected = PwsUserConfig.builder()
                .specificApp(false)
                .specificUrl(true)
                .specificPwsSetting(true)
                .build();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getPwsUserConfig_with_wrongUserIdAndPwsId() {
        var actual = userService.getPwsUserConfig(100L, 100L);
        var expected = PwsUserConfig.builder()
                .specificApp(false)
                .specificUrl(false)
                .specificPwsSetting(false)
                .build();
        Assertions.assertEquals(expected, actual);
    }
}