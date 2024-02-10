package com.tohjiwa.teamsync.server.dao.workspace.personal;

import com.tohjiwa.teamsync.server.constant.Status;
import com.tohjiwa.teamsync.server.entity.workspace.personal.PwsTimeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface PwsTimeLogRepository extends JpaRepository<PwsTimeLogEntity, Long> {
    @Query("SELECT SUM(m.sessionTotalTime) FROM PwsTimeLogEntity m where m.user.id = ?1 and m.pws.id = ?2 and m.status = ?3 and m.sessionStart >= ?4 and m.sessionEnd <= ?5")
    Long sumTotalTimeBetween(Long userId, Long pwsId, Status status, Date after, Date before);
}