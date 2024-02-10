package com.tohjiwa.teamsync.server.dao;

import com.tohjiwa.teamsync.server.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
}