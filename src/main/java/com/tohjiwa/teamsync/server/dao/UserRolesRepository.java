package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoleEntity, Long> {
}