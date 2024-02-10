package com.tohjiwa.teamsync.server.dao.plan;

import com.tohjiwa.teamsync.server.entity.plan.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Byte> {
}