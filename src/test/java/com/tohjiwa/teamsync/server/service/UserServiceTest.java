package com.tohjiwa.teamsync.server.service;

import com.tohjiwa.teamsync.server.constant.UserStatus;
import com.tohjiwa.teamsync.server.dao.UserRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserConfigRepository;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsEntity;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsUserConfigEntity;
import com.tohjiwa.teamsync.server.model.User;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    @Spy
    private Validator validator;

    {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Spy
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Mock
    private UserRepository userRepository;
    @Mock
    private PwsUserConfigRepository pwsUserConfigRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        var userEntity = UserEntity.builder()
                .id(-1L)
                .email("user@tmsync.co")
                .firstName("User")
                .lastName("Lastname")
                .phoneNumber("0000")
                .phoneNumberCountryCode("+1")
                .username("user")
                .password("12345678")
                .build();
        Mockito.when(userRepository.findById(-1L)).thenReturn(Optional.of(userEntity));

        PwsUserConfigEntity expectedData = PwsUserConfigEntity.builder()
                .user(UserEntity.builder().id(1L).build())
                .pws(PwsEntity.builder().id(1L).build())
                .userConfiguration(PwsUserConfig.builder()
                        .specificApp(true)
                        .specificUrl(true)
                        .specificPwsSetting(true)
                        .build())
                .build();
        Mockito.when(pwsUserConfigRepository.findByUser_IdAndPws_Id(1L, 1L)).thenReturn(Optional.of(expectedData));

        var userEntity1 = UserEntity.builder()
                .id(100L)
                .email("user@tmsync.co")
                .firstName("First")
                .lastName("Last")
                .password("password")
                .build();
        userEntity1.setUserStatus(UserStatus.ACTIVE);
        userEntity1.setCreatedDate(new Date(1000L));
        Mockito.when(userRepository.save(ArgumentMatchers.any(UserEntity.class))).thenReturn(userEntity1);

        Mockito.when(passwordEncoder.encode("password")).thenReturn("password123");
    }

    @Test
    void getUser() {
        var userRsl = userService.getUser(-1L);
        Assertions.assertTrue(userRsl.isPresent());

        Assertions.assertEquals(-1L, userRsl.get().getId());
        Assertions.assertEquals("user@tmsync.co", userRsl.get().getEmail());
        Assertions.assertEquals("User", userRsl.get().getFirstName());
        Assertions.assertEquals("Lastname", userRsl.get().getLastName());
        Assertions.assertEquals("0000", userRsl.get().getPhoneNumber());
        Assertions.assertEquals("+1", userRsl.get().getPhoneNumberCountryCode());
        Assertions.assertEquals("user", userRsl.get().getUsername());
        Assertions.assertEquals("12345678", userRsl.get().getPassword());
    }

    @Test
    void getUser_with_UserIdNotExist() {
        var userRsl = userService.getUser(-99L);
        Assertions.assertFalse(userRsl.isPresent());
    }

    @Test
    void getUserStatus() {
    }

    @Test
    void register() throws Exception {
        var user = User.builder()
                .id(2L)
                .email("user@tmsync.co")
                .firstName("First")
                .lastName("Last")
                .password("password")
                .build();

        var actual = userService.register(user);

        var expected = User.builder()
                .id(100L)
                .createdDate(new Date(1000L))
                .email("user@tmsync.co")
                .firstName("First")
                .lastName("Last")
                .password("password")
                .build();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void register_with_Password1Char() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .id(2L)
                    .email("user@tmsync.co")
                    .firstName("")
                    .lastName("Last")
                    .password("1")
                    .build();

            userService.register(user);
        });

        Assertions.assertEquals("Password min 8 character and max 32 character", exception.getMessage());
    }

    @Test
    void register_with_Password50Char() {
        Throwable exception = assertThrows(Exception.class, () -> {
            var user = User.builder()
                    .id(2L)
                    .email("user@tmsync.co")
                    .firstName("")
                    .lastName("Last")
                    .password("12345678901011121314151617181920212223242526272829")
                    .build();
            userService.register(user);
        });

        Assertions.assertEquals("Password min 8 character and max 32 character", exception.getMessage());
    }

    @Test
    void getPwsUserConfig() {
        var pwsUserConfig = userService.getPwsUserConfig(1L, 1L);
        var expected = new PwsUserConfig(true, true, true);
        Assertions.assertEquals(expected, pwsUserConfig);
    }
}