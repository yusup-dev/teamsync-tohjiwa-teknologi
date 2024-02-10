package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Byte> {
}