package com.tohjiwa.teamsync.server.dao.plan;

import com.tohjiwa.teamsync.server.entity.plan.PlanFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanFeatureRepository extends JpaRepository<PlanFeatureEntity, Long> {
}