package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PwsActivityLogRepository extends JpaRepository<PwsActivityLogEntity, Long> {
}
