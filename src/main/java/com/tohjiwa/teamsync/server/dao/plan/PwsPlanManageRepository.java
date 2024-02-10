package com.tohjiwa.teamsync.server.dao.plan;

import com.tohjiwa.teamsync.server.entity.plan.PwsPlanManageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PwsPlanManageRepository extends JpaRepository<PwsPlanManageEntity, Long> {
}