package com.tohjiwa.teamsync.server.service;

import com.tohjiwa.teamsync.server.constant.UserStatus;
import com.tohjiwa.teamsync.server.dao.UserDetailRepository;
import com.tohjiwa.teamsync.server.dao.UserRepository;
import com.tohjiwa.teamsync.server.dao.workspace.personal.PwsUserConfigRepository;
import com.tohjiwa.teamsync.server.dto.UserUpdateReqDTO;
import com.tohjiwa.teamsync.server.entity.UserDetailEntity;
import com.tohjiwa.teamsync.server.entity.UserEntity;
import com.tohjiwa.teamsync.server.exception.InvalidRequestException;
import com.tohjiwa.teamsync.server.model.User;
import com.tohjiwa.teamsync.server.model.UserProfile;
import com.tohjiwa.teamsync.server.model.validator.ManualValidationGroup;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
import jakarta.validation.Validator;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final PwsUserConfigRepository pwsUserConfigRepository;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDetailRepository userDetailRepository, PwsUserConfigRepository pwsUserConfigRepository, Validator validator, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.pwsUserConfigRepository = pwsUserConfigRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Cacheable(value = "UserStatus")
    public Optional<UserStatus> getUserStatus(Long userId) {
        var userEntity = userRepository.findById(userId);
        return userEntity.map(UserEntity::getUserStatus);
    }

    public Optional<User> getUser(Long userId) {
        var userRsl = userRepository.findById(userId);
        return userRsl.map(userEntity -> modelMapper.map(userEntity, User.class));
    }

    public User register(User user) throws Exception {
        var userEntity = UserEntity.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        //Validate password
        var violations = validator.validate(userEntity, ManualValidationGroup.class);

        if (!violations.isEmpty()) {
            var violation = violations.stream().findFirst();
            System.out.println(violation.get().getMessage());
            throw new Exception(violation.get().getMessage());
        }

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        try {
            var userEntityRsl = userRepository.save(userEntity);
            return modelMapper.map(userEntityRsl, User.class);
        } catch (DataIntegrityViolationException ex) {
            logger.error(ex.getMessage());
            if (ex.getCause() instanceof ConstraintViolationException con) {
                if (con.getErrorCode() == 1062 || con.getErrorCode() == 23505) {
                    throw new InvalidRequestException("email have ben used in other account");
                }
            }

            throw ex;
        } catch (jakarta.validation.ConstraintViolationException ex) {
            var violation = ex.getConstraintViolations().stream().findFirst();
            if (violation.isPresent() && violation.stream().findFirst().isPresent())
                throw new Exception(violation.stream().findFirst().get().getMessage());

            throw ex;
        }
    }

    public Optional<UserProfile> getUserProfile(Long userId) {
        var userEntity = userRepository.findById(userId);
        var userDetailEntity = userDetailRepository.findByUser_Id(userId);
        if(userEntity.isEmpty()) {
            return Optional.empty();
        }

        var userProfile = modelMapper.map(userEntity, UserProfile.class);
        userDetailEntity.ifPresent(detailEntity -> modelMapper.map(detailEntity, userProfile));
        userProfile.setCreatedDate(userEntity.get().getCreatedDate());

        return Optional.of(userProfile);
    }

    public UserProfile updateUserProfile(UserProfile userProfile) {
        var userEntity = userRepository.findById(userProfile.getUserId());
        var userDetailEntity = userDetailRepository.findByUser_Id(userProfile.getUserId());
        if(userEntity.isEmpty()) {
            throw new InvalidRequestException("Can't update user profile, user with id " + userProfile.getUserId() + " not found.");
        }

        if(userDetailEntity.isEmpty()) {
            userDetailEntity = Optional.of(new UserDetailEntity());
            userDetailEntity.get().setUser(userEntity.get());
        }

        userEntity.get().setFirstName(userProfile.getFirstName());
        userEntity.get().setLastName(userProfile.getLastName());
        userEntity.get().setPhoneNumberCountryCode(userProfile.getPhoneNumberCountryCode());
        userEntity.get().setPhoneNumber(userProfile.getPhoneNumber());

        userDetailEntity.get().setSkills(userProfile.getSkills());
        userDetailEntity.get().setCompany(userProfile.getCompany());
        userDetailEntity.get().setDesignation(userProfile.getDesignation());
        userDetailEntity.get().setUrl(userProfile.getUrl());
        userDetailEntity.get().setCity(userProfile.getCity());
        userDetailEntity.get().setCountry(userProfile.getCountry());
        userDetailEntity.get().setZipCode(userProfile.getZipCode());
        userDetailEntity.get().setBio(userProfile.getBio());

        userDetailEntity.get().setSocialUrlLinkedin(userProfile.getSocialUrlLinkedin());
        userDetailEntity.get().setSocialUrlGithub(userProfile.getSocialUrlGithub());
        userDetailEntity.get().setSocialUrlDribble(userProfile.getSocialUrlDribble());
        userDetailEntity.get().setSocialUrlPinterest(userProfile.getSocialUrlPinterest());
        userDetailEntity.get().setSocialUrlTwitter(userProfile.getSocialUrlTwitter());
        userDetailEntity.get().setSocialUrlFacebook(userProfile.getSocialUrlFacebook());
        userDetailEntity.get().setSocialUrlInstagram(userProfile.getSocialUrlInstagram());

        var userEntityRsl = userRepository.save(userEntity.get());
        var userDetailEntityRsl = userDetailRepository.save(userDetailEntity.get());

        var userProfileRsl = modelMapper.map(userEntityRsl, UserProfile.class);
        modelMapper.map(userDetailEntityRsl, userProfileRsl);

        return userProfileRsl;
    }

    public PwsUserConfig getPwsUserConfig(Long userId, Long pwsId) {
        var pwsUserConfigEntity = pwsUserConfigRepository.findByUser_IdAndPws_Id(userId, pwsId);
        if (pwsUserConfigEntity.isPresent()) {
            return pwsUserConfigEntity.get().getUserConfiguration();
        } else {
            return new PwsUserConfig(false, false, false);
        }
    }
}
