package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> {
    Optional<UserDetailEntity> findByUser_Id(Long id);
}
