package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.UrlCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlCategoryRepository extends JpaRepository<UrlCategoryEntity, Long> {
}