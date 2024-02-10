package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.AppNewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppNewRepository extends JpaRepository<AppNewEntity, Long> {
}