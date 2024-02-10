package com.tohjiwa.teamsync.server.dao.plan;

import com.tohjiwa.teamsync.server.entity.plan.UserPaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethodEntity, Long> {
}