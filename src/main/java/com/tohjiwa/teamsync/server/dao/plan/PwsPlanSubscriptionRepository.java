package com.tohjiwa.teamsync.server.dao.plan;

import com.tohjiwa.teamsync.server.entity.plan.PwsPlanSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PwsPlanSubscriptionRepository extends JpaRepository<PwsPlanSubscriptionEntity, Long> {
}