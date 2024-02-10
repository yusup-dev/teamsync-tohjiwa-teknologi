package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<AppEntity, Long> {
}