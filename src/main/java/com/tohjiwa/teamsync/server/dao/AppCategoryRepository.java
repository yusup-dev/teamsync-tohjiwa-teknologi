package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.AppCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppCategoryRepository extends JpaRepository<AppCategoryEntity, Long> {
}